package top.sl.common.app;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

import butterknife.ButterKnife;

public abstract class Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面未初始化之前调用的初始化窗口
        initWidows();

        if (initArgs(getIntent().getExtras())) {
            // 得到界面Id并设置到Activity界面中
            int layId = getContentLayoutId();
            setContentView(layId);

            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWidows(){

}
    /**
     * 得到当前的资源文件ID
     * @return 资源文件ID
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化是否成功
     * @param bundle
     * @return
     */
    protected boolean initArgs(Bundle bundle){
        return true;
    }

    /**
     * 初始化控件
     */
    protected void initWidget() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //得到activity下所有fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        //判断是否为空
        if (fragments!=null&&fragments.size()>0){
            //遍历判断是否为我们能处理的fragment
            for (Fragment fragment:fragments){
                if (fragment instanceof MyFragment){
                    //判断是否拦截了返回按钮
                    if(((MyFragment) fragment).onBackPressed()){
                        //若果有直接return
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
    }
}
