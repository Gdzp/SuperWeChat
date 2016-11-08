package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Think on 2016/11/7.
 */

public class FriendProfileActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.tv_userinfo_nick)
    TextView tvUserinfoNick;
    @BindView(R.id.tv_userinfo_name)
    TextView tvUserinfoName;
    User user = null;
    @BindView(R.id.btn_add_contact)
    Button btnAddContact;
    @BindView(R.id.btn_send_msg)
    Button btnSendMsg;
    @BindView(R.id.btn_send_video)
    Button btnSendVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user == null) {
            MFGT.finish(this);
        }
        initView();


    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.userinfo_txt_profile);
        setUserInfo();
        isFriend();
    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), profileImage);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), tvUserinfoNick);
        EaseUserUtils.setAppUserNameWithNo(user.getMUserName(), tvUserinfoName);
    }


    public void isFriend() {
        if (SuperWeChatHelper.getInstance().getAppContactList().containsKey(user.getMUserName())) {
            btnSendMsg.setVisibility(View.VISIBLE);
            btnSendVideo.setVisibility(View.VISIBLE);
        } else {
            btnAddContact.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.img_back, R.id.btn_add_contact, R.id.btn_send_msg, R.id.btn_send_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(this);
                break;
            case R.id.btn_add_contact:
                MFGT.gotoAddFirendMsg(this,user.getMUserName());
                break;
            case R.id.btn_send_msg:
               MFGT.gotoChat(this,user.getMUserName());
                break;
            case R.id.btn_send_video:
                break;
        }
    }
}

