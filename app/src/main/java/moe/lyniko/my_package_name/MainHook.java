package moe.lyniko.my_package_name;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedBridge;
public class MainHook implements IXposedHookLoadPackage, IXposedHookZygoteInit {
    public static final String TAG = "REPLACE-TAG";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        XposedBridge.log("Init.Current pkg: " + lpparam.packageName);
        if ("com.package.name.target".equals(lpparam.packageName)) {
            new REPLACE_HOOK().handleLoadPackage(lpparam);
        }
    }
    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {}
}
