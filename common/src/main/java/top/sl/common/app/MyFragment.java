package top.sl.common.app;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MyFragment extends Fragment {
    protected View root;
    protected Unbinder rootUnbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root==null){
            int layoutId = getContentLayoutId();
            //初始化当前的根布局,但是不在创建时就添加到container
            View view = inflater.inflate(layoutId, container, false);
            initWidget(view);
            root=view;
        }else {
            if (root.getParent()!=null){
                ((ViewGroup)root.getParent()).removeView(root);
            }
        }
        return root;
    }

    protected abstract int getContentLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected void initWidget(View view) {
        rootUnbinder=ButterKnife.bind(this,view);
    }
    protected boolean initArgs(Bundle bundle){
        return true;
    }
    protected void initData() {

    }

    /**
     * 返回按键触发时调用
     * @return 返回true代表我已处理返回逻辑，activity不用自己finish 返回false代表我没有处理activity自己走自己逻辑
     */
    public boolean onBackPressed(){
        return false;
    }
}
