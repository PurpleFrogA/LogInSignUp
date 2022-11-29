package com.example.loginsignup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUPFragment extends Fragment {
    private Button btsignup;
    private EditText email, password, confirmpassword;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUPFragment newInstance(String param1, String param2) {
        SignUPFragment fragment = new SignUPFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_u_p, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {
        btsignup = getView().findViewById(R.id.bSignUpf);
        email = getView().findViewById(R.id.etEmailMain);
        password = getView().findViewById(R.id.etPasswordMain);
        confirmpassword = getView().findViewById(R.id.etPassword2Main);
        fbs = FirebaseServices.getInstance();
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailstr, passwordstr, confirmpasswordstr;
                emailstr = email.getText().toString();
                passwordstr = password.getText().toString();
                confirmpasswordstr = confirmpassword.getText().toString();

                if (emailstr.trim().isEmpty() || passwordstr.trim().isEmpty() || confirmpasswordstr.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "you didn't entered all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmailValid(emailstr)) {
                    Toast.makeText(getActivity(), "Email is incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isPasswordValid(passwordstr)) {
                    Toast.makeText(getActivity(), "password should be written in another way", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!passwordstr.equals(confirmpasswordstr)){
                    Toast.makeText(getActivity(), "passwords are not identical", Toast.LENGTH_SHORT).show();
                }
                fbs.getAuth().createUserWithEmailAndPassword(emailstr,passwordstr)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.frameLayout, new BookFragment());
                                    ft.commit();
                                }
                                else {
                                    Log.e("TAG", task.getException().getMessage());
                                    Toast.makeText(getActivity(), "Username or Password incorrect", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                // TODO: sign up to system

            }

            private boolean isPasswordValid(String password) {
                /*
                Pattern pattern;
                Matcher matcher;
                final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(password);

                return matcher.matches();
                */

                return true;
            }

            public boolean isEmailValid(String st)
            {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(st);
                return matcher.matches();
            }
            });
        ;}
    }