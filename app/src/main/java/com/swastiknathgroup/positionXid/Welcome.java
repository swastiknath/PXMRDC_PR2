package com.swastiknathgroup.positionXid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint.Style;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.AuthUI.IdpConfig;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Welcome extends AppCompatActivity {
private Button signout;
private FirebaseAuth mAuth;
private FirebaseAuth.AuthStateListener mAuthstateListener;
private TextView text1;
private TextView txt2;
private TextView tx3;
private TextView tex4;
private Button send_email_verify;
private TextView tex5;
private Button deleteAccount;
private Button mpasswordreset;
private LinearLayout layout;
private EditText update_pass;
private Button update_p;
private EditText displayname;
private Button changename;
private Button namechangesave;
private  LinearLayout layout2;
    private static final int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth=FirebaseAuth.getInstance();
        text1=(TextView)findViewById(R.id.tex1);
        txt2=(TextView)findViewById(R.id.text2);
        tex5=(TextView)findViewById(R.id.verifiedcred);
        tx3=(TextView)findViewById(R.id.tex3);
        signout=(Button)findViewById(R.id.signout);
        tex4=(TextView)findViewById(R.id.last_sign_in);
        layout2=(LinearLayout)findViewById(R.id.displayname_edit_container);
        layout2.setVisibility(View.GONE);
        layout=(LinearLayout)findViewById(R.id.edit_container);
        layout.setVisibility(View.GONE);
        update_pass=(EditText)findViewById(R.id.update_pass);
        update_p=(Button)findViewById(R.id.button_update_pass);
        update_p.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String newpass=update_pass.getText().toString().trim();
                if(TextUtils.isEmpty(newpass)){Toast.makeText(Welcome.this,"Password can't be empty",Toast.LENGTH_LONG).show();
                layout.setVisibility(View.GONE);
                }else{
               mAuth.getCurrentUser().updatePassword(update_pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                           layout.setVisibility(View.GONE);
                           Toast.makeText(Welcome.this,"Passphrase Changed Successfully",Toast.LENGTH_LONG).show();
                       }else{
                           layout.setVisibility(View.GONE);
                           Toast.makeText(Welcome.this,"Error Changing Password",Toast.LENGTH_LONG).show();
                       }
                   }
               });
                }
            }
        });


//        String setdisplayname="kukur";
//     UserProfileChangeRequest.Builder usm=new UserProfileChangeRequest.Builder();
//     usm.setDisplayName(setdisplayname).build();
//
//     UserProfileChangeRequest userProfileChangeRequest=usm.build();

     mpasswordreset=(Button)findViewById(R.id.change_cred);
        mpasswordreset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert=new AlertDialog.Builder(Welcome.this);
                alert.setTitle("Change Passphrase ?").setMessage("This Action will change your passphrase and is irrevocable. Are you sure to Continue?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setPositiveButton("Change Passphrase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        layout.setVisibility(View.VISIBLE);
                    }
                });
                AlertDialog al=alert.create();
                al.show();
            }
        });
        deleteAccount =(Button)findViewById(R.id.delete_account);
        deleteAccount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alt=new AlertDialog.Builder(Welcome.this);
                alt.setTitle(R.string.delete_title).setMessage(R.string.delete_message).setPositiveButton(R.string.delete_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AuthUI.getInstance().delete(Welcome.this);
                    }
                }).setNegativeButton(R.string.delete_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog altdialog=alt.create();
                altdialog.show();
            }
        });
        send_email_verify=(Button)findViewById(R.id.verify_email);
        send_email_verify.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                        Toast.makeText(Welcome.this,"Verification Email Had been sent",Toast.LENGTH_LONG).show();
                        tex5.setText("Credential Verification Process Started: Email Address");
                    }}
                });
            }
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        signout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(Welcome.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Welcome.this, "Signing Out Successful", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        });
        mAuthstateListener=new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=mAuth.getCurrentUser();
                if(firebaseUser!=null){
                    updateui(firebaseUser);
                }else{
                    startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build(),
                                                             new AuthUI.IdpConfig.EmailBuilder().build(),
                                                              new AuthUI.IdpConfig.PhoneBuilder().build()))
                    .setTosAndPrivacyPolicyUrls("https://swastiknathgroup.com/privacy-statement","https://swastiknathgroup.com/privacy-statement")
                            .setTheme(R.style.AppTheme_PostionX_ID_SUPPORT)
                            .build(),RC_SIGN_IN);
                }
            }
        };

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthstateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthstateListener);
    }

    public void updateui(FirebaseUser user){
     long timeofsignin=user.getMetadata().getLastSignInTimestamp();
        Date date=new Date(timeofsignin);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss", Locale.getDefault());
        dateFormat.format(date);
        text1.setText("Welcome, "+user.getDisplayName());
        if(user.getEmail()!=null){
        txt2.setText("Registered Email ID: "+user.getEmail());
        } else{
            txt2.setText("Registered Phone Number"+user.getPhoneNumber());
        }
        tx3.setText("PositionX ID Number: "+user.getUid());
        tex4.setText("Last Signed In at: "+date.toString());
        if(user.isEmailVerified()){
        tex5.setText("Credential Verified: Email ID Verfication Successful");
        }else{tex5.setText("Cerdential Not Verified");}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                finish();
            }
        }

    }
}
