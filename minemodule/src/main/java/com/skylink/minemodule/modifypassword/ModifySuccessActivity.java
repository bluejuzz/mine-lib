package com.skylink.minemodule.modifypassword;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.skylink.android.commonlibrary.base.BaseActivity;
import com.skylink.android.commonlibrary.ui.AppHeader;
import com.skylink.minemodule.R;


/**
 * 修改成功
 * @author fangj
 */
public class ModifySuccessActivity extends BaseActivity {

    public static final int FROM_MOD_PHONE = 3;
    public static final int FROM_MOD_PASSWORD = 1;

    private AppHeader header;
    private TextView tv_mod_success;
    private Button button_confirm;
    private int type;

    @Override
    protected void receiveParms(Bundle parms) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            type = bundle.getInt("from",1);
        }
    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_modify_success;
    }

    @Override
    protected void initView(View view) {
        header = view.findViewById(R.id.success_header);
        tv_mod_success = view.findViewById(R.id.tv_mod_success);
        button_confirm = view.findViewById(R.id.button_confirm);
        switch (type){
            case FROM_MOD_PASSWORD:
                header.setTitle("修改密码");
                tv_mod_success.setText("修改密码成功");
                button_confirm.setVisibility(View.VISIBLE);
                showToast("请重新登录，否则更改的密码无效！");
                break;
            case FROM_MOD_PHONE:
                header.setTitle("修改手机号码");
                tv_mod_success.setText("修改手机号码成功");
                break;
            default:
                header.setTitle("标题");
                tv_mod_success.setText("修改成功");
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //修改密码成功
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.skylink.pdavender","com.skylink.pdavender.login.LoginActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                showToast("退出成功");
                finish();
            }
        });
        header.setHeaderClickListener(new AppHeader.OnHeaderButtonClickListener() {
            @Override
            public void onLeftButtonClick() {
                finish();
            }

            @Override
            public void onMiddleButtonClick() {

            }

            @Override
            public void onRightButtonClick() {

            }
        });
    }
}
