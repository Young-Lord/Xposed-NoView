package moe.lyniko.my_package_name;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class REPLACE_HOOK implements IXposedHookLoadPackage {
//    XSharedPreferences prefs = new XSharedPreferences(BuildConfig.APPLICATION_ID, "conf");

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if ("com.package.name.target".equals(lpparam.packageName)) {
            try {
                XposedHelpers.findAndHookMethod("com.package.name.target.REPLACE-CLASSNAME", lpparam.classLoader, "REPLACE-METHOD-NAME", new XC_MethodHook() {
                // 上一行的 new 前记得加上参数class列表
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        int pa1 = (int)param.args[0];
                        if (pa1 == 0) {
                            param.setResult(null); // 跳过原方法
                        }
                    }
/*                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return true; // 也是跳过原方法并返回指定值，自行根据需要注释
                    }
                    // 注意，这个需要把上面的 XC_MethodHook 改为 XC_MethodReplacement
                    */
                });
            } catch (Throwable t) {
                XposedBridge.log(t);
            }
        }

    }

}