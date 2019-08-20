package top.sl.moon;

import android.widget.TextView;

import butterknife.BindView;
import top.sl.common.app.Activity;

public class MainActivity extends Activity {
    @BindView(R.id.textView)
    TextView mTestText;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTestText.setText("Test hello.");
    }
}
