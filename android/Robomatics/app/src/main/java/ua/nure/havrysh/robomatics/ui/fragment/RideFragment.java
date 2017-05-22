package ua.nure.havrysh.robomatics.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import butterknife.BindView;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.presenter.BasePresenter;
import ua.nure.havrysh.robomatics.presenter.RidePresenter;
import ua.nure.havrysh.robomatics.ui.activity.RideActivity;
import ua.nure.havrysh.robomatics.ui.widget.BigSeekBar;
import ua.nure.havrysh.robomatics.utils.DataSender;
import ua.nure.havrysh.robomatics.utils.InputProviderModel;
import ua.nure.havrysh.robomatics.utils.Normalizer;
import ua.nure.havrysh.robomatics.utils.OutputConsumerModel;
import ua.nure.havrysh.robomatics.utils.OutputParams;
import ua.nure.havrysh.robomatics.utils.RhinoUtils;
import ua.nure.havrysh.robomatics.utils.SeekResetter;

public class RideFragment extends BaseFragment {
    private static final double MAX_SEEK_BAR_VALUE = 100;

    private static final String PREF_INTERVAL = "interval";
    private static final String PREF_ADDRESS = "address";
    private static final String PREF_LEFT = "left";
    private static final String PREF_RIGHT = "right";

    private static final String CODE_ARG = "CODE_ARG";

    @BindView(R.id.debug)
    TextView debugText;

    @BindView(R.id.seekSteer)
    BigSeekBar steeringSeekBar;

    @BindView(R.id.seekThrottle)
    BigSeekBar throttleSeekBar;

    @Inject
    RidePresenter ridePresenter;
    //Rhino
    private Context context;
    private Scriptable scope;
    private DataSender dataSender;
    private InputProviderModel inputProviderModel;
    private OutputConsumerModel outputConsumerModel;

    private OutputParams outputParams;

    public static RideFragment newInstance(RideActivity rideActivity) {
        RideFragment rideFragment = new RideFragment();
        rideFragment.setArguments(getArgsFromActivity(rideActivity));
        return rideFragment;
    }

    public static Bundle newBundle(String code) {
        Bundle bundle = new Bundle();
        bundle.putString(CODE_ARG, code);
        return bundle;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        steeringSeekBar.setHorizontal(true);
        throttleSeekBar.setOnTouchListener(new SeekResetter((int) (MAX_SEEK_BAR_VALUE / 2)));
        steeringSeekBar.setOnTouchListener(new SeekResetter((int) (MAX_SEEK_BAR_VALUE / 2)));

        throttleSeekBar.setChangedListener(() -> {
            if (inputProviderModel != null) {
                inputProviderModel.setSeekV(getThrottle());
            }
        });

        steeringSeekBar.setChangedListener(() -> {
            if (inputProviderModel != null) {
                inputProviderModel.setSeekH(getSteer());
            }
        });
    }

    private void initScriptEngine() {
        context = RhinoAndroidHelper.prepareContext();
        context.setOptimizationLevel(-1);
        scope = context.initStandardObjects();
        try {
            ScriptableObject.defineClass(scope, InputProviderModel.class);
            ScriptableObject.defineClass(scope, OutputConsumerModel.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        inputProviderModel = (InputProviderModel) context.newObject(scope, InputProviderModel.NAME);
        outputConsumerModel = (OutputConsumerModel) context.newObject(scope, OutputConsumerModel.NAME);
        scope.put("input", scope, inputProviderModel);
        scope.put("output", scope, outputConsumerModel);
        outputParams.setOutput(outputConsumerModel);
        String code = getArguments().getString(CODE_ARG) + RhinoUtils.MAIN_LOOP;
        context.executeScriptWithContinuations(context.compileString(code, "CODE", 0, null), scope);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {


            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            outputParams = new OutputParams(new Normalizer(Integer.valueOf(preferences.getString(PREF_LEFT, "80")),
                    Integer.valueOf(preferences.getString(PREF_RIGHT, "100"))),
                    new Normalizer(0, 255));
            new Thread(this::initScriptEngine).start();
        }
    }

    @Override
    protected void setupToolbar(Toolbar toolbar, ActionBar actionBar) {
        super.setupToolbar(toolbar, actionBar);
        toolbar.setVisibility(View.GONE);
    }

    private double normalize(int value) {
        return value / MAX_SEEK_BAR_VALUE * 2 - 1;
    }

    double getSteer() {
        return normalize(steeringSeekBar.getProgress());
    }

    double getThrottle() {
        return normalize(throttleSeekBar.getProgress());
    }

    @Override
    public void onResume() {
        super.onResume();
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        final int interval = Integer.valueOf(preferences.getString(PREF_INTERVAL, "100"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataSender = new DataSender(preferences.getString(PREF_ADDRESS, "192.168.4.1:81"), outputParams);
                } catch (final IOException e) {
                    new Handler().post(() -> Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show());
                    return;
                    //throw new RuntimeException(e);
                }
                dataSender.setDebugView(debugText);
                dataSender.setInterval(interval);
                dataSender.execute();
            }
        }).start();
    }

    @Override
    public void onPause() {
        if (dataSender != null) {
            try {
                dataSender.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onPause();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ride;
    }

    @Override
    protected BasePresenter injectDependencies(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        return ridePresenter;
    }
}
