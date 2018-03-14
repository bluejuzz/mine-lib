package com.skylink.minemodule.modifypassword;


import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.skylink.android.commonlibrary.base.BaseActivity;
import com.skylink.android.commonlibrary.base.BaseResponse;
import com.skylink.android.commonlibrary.constant.SPContants;
import com.skylink.android.commonlibrary.entity.LoginResponse;
import com.skylink.android.commonlibrary.netutil.RetrofitCallback;
import com.skylink.android.commonlibrary.netutil.RetrofitUtils;
import com.skylink.android.commonlibrary.ui.AppHeader;
import com.skylink.android.commonlibrary.ui.ClearEditText;
import com.skylink.android.commonlibrary.util.SPUtils;
import com.skylink.android.commonlibrary.util.StringUtils;
import com.skylink.minemodule.R;
import com.skylink.minemodule.common.MineService;
import com.skylink.minemodule.modifypassword.bean.ModifyPswdRequest;

import retrofit2.Call;

import static android.R.id.message;

/**
 * 修改密码
 * @author fangj
 */
public class ModifyPasswordActivity extends BaseActivity {

    /** 旧密码 **/
    private ClearEditText edit_opassword;

    /** 新密码 **/
    private ClearEditText edit_npassword;

    /** 再次输入密码 **/
    private ClearEditText edit_rpassword;

    /** 确认修改 **/
    private Button button_confirm;

    /**是否显示密码**/
    private CheckBox show_password;

    private AppHeader mHeader;
    @Override
    protected void receiveParms(Bundle parms) {

    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initView(View view) {
        edit_opassword = view.findViewById(R.id.changepassword_edit_opassword);
        edit_npassword = view.findViewById(R.id.changepassword_edit_npassword);
        edit_rpassword = view.findViewById(R.id.changepassword_edit_rpassword);
        button_confirm = view.findViewById(R.id.changepassword_button_confirm);
        mHeader = view.findViewById(R.id.changepassword_header);
        show_password = f(R.id.changepassword_img_show_password);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mHeader.setHeaderClickListener(new AppHeader.OnHeaderButtonClickListener() {
            @Override
            public void onLeftButtonClick() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }

            @Override
            public void onMiddleButtonClick() {

            }

            @Override
            public void onRightButtonClick() {

            }
        });

        //确认修改
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyPassword(edit_opassword.getText().toString().trim(),edit_npassword.getText().toString().trim(),edit_rpassword.getText().toString().trim());
            }
        });

        //显示隐藏密码
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 显示密码
                    edit_opassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edit_opassword.setSelection(edit_opassword.getText().toString().length());
                    edit_npassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edit_npassword.setSelection(edit_npassword.getText().toString().length());
                    edit_rpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edit_rpassword.setSelection(edit_rpassword.getText().toString().length());
                }
                else {
                    // 隐藏密码
                    edit_opassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edit_opassword.setSelection(edit_opassword.getText().toString().length());
                    edit_npassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edit_npassword.setSelection(edit_npassword.getText().toString().length());
                    edit_rpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edit_rpassword.setSelection(edit_rpassword.getText().toString().length());
                }
            }
        });
    }

    /***
     * 修改密码
     * @param opwd
     * @param npwd
     * @param rpwd
     */
    private void modifyPassword(String opwd, final String npwd, String rpwd) {
        if (!validationData(opwd,npwd,rpwd)){
            return;
        }
        ModifyPswdRequest request=new ModifyPswdRequest();
        request.setPswd(opwd);
        request.setNewpswd(npwd);
        Call<BaseResponse> responseCall = RetrofitUtils.getDefaultRetrofitInstance().create(MineService.class).modifyPassword(request.getPswd(),request.getNewpswd());
        RetrofitUtils.requestData(responseCall, new RetrofitCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
              if (response.isSuccess()){
                  LoginResponse loginResponse = null;
                  String loginInfoStr = SPUtils.getInstance(SPContants.APP_SP_NAME).getString(SPContants.LOGIN_INFO);
                  if (null != loginInfoStr) {
                      try {
                          loginResponse = new Gson().fromJson(loginInfoStr, new TypeToken<LoginResponse>() {
                          }.getType());
                      } catch (JsonSyntaxException e) {
                          e.printStackTrace();
                      }
                      loginResponse.getUserinfo().setPassword("");
                      loginResponse.getUserinfo().setLocalPassword(npwd);
                      String loginResponseStr = new Gson().toJson(loginResponse);
                      SPUtils.getInstance(SPContants.APP_SP_NAME).put(SPContants.LOGIN_INFO, loginResponseStr);
                      skipSuccess();
                  }
              }
            }

            @Override
            public void onFail(String message) {
                   showErr(message);
            }

            @Override
            public void onFinish() {

            }
        });

    }

    /***
     * 跳到修改成功界面
     */
    private void skipSuccess() {
        Bundle bundle = new Bundle();
        bundle.putInt("from", ModifySuccessActivity.FROM_MOD_PASSWORD);
        startActivity(ModifySuccessActivity.class, bundle);
    }

    private boolean validationData(String opwd,String npwd,String rpwd){
        boolean result=true;

        if (StringUtils.isEmpty(opwd)) {
            showErr("原密码不能为空！");
            return result = false;

        } else if (StringUtils.getZHCount(opwd) > 0 || opwd.contains(" ")) {
            showErr("原密码不能包含中文和空格！");
            return result = false;
        }

        if ((opwd.length() < 6) || (opwd.length() > 20)) {
            showErr("原密码长度为6-20位！");
            return result = false;
        }

        if ((npwd == null) || (npwd.equalsIgnoreCase(""))) {
            showErr("新密码不能为空！");
            return result = false;

        } else if (StringUtils.getZHCount(npwd) > 0 || npwd.contains(" ")) {
            showErr("新密码不能包含中文和空格！");
            return result = false;
        }

        if ((npwd.length() < 6) || (npwd.length() > 20)) {
            showErr("新密码长度为6-20位！");
            return result = false;
        }

        if ((rpwd == null) || (rpwd.equalsIgnoreCase(""))) {
            showErr("确认密码不能为空！");
            return result = false;
        }
        if ((rpwd.length() < 6) || (rpwd.length() > 20)) {
            showErr("确认密码长度为6-20位！");
            return result = false;
        }
        if (!npwd.endsWith(rpwd)) {
            showErr("两次输入的新密码不一致！");
            return result = false;
        }
        return result =true;
    }

}
