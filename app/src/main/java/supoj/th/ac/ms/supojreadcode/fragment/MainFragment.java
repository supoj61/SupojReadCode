package supoj.th.ac.ms.supojreadcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import supoj.th.ac.ms.supojreadcode.R;
import supoj.th.ac.ms.supojreadcode.utility.GetAllUser;
import supoj.th.ac.ms.supojreadcode.utility.MyAlert;
import supoj.th.ac.ms.supojreadcode.utility.MyConstant;

/**
 * Created by SUPOJ on 20.03.2018.
 */

public class MainFragment extends Fragment{


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // Register Controller
        registerController();

//Login Controller
        loginController();

    }  ///Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.message_title),getString(R.string.message_detail));
                } else {
                    try {

                        MyConstant myConstant = new MyConstant();
                        GetAllUser getAllUser = new GetAllUser(getActivity());
                        getAllUser.execute(myConstant.getUrlGetAllUser());

                        String jsonString= getAllUser.get();
                        Log.d("22MarchV1", "JSON ==>" + jsonString);

                        String[] columUserStrings = myConstant.getLoginStrings();
                        String[] loginStrings = new String[columUserStrings.length];
                        boolean statusBool = true;
                        JSONArray jsonArray = new JSONArray(jsonString);

                        for (int i=0;i<jsonArray.length();i+=1) {

                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            if(userString.equals(jsonObject.getString(columUserStrings[2]))){
                                statusBool=false;
                                for (int i1=0;i1<columUserStrings.length;i1+=1) {
                                    loginStrings[i1] = jsonObject.getString(columUserStrings[i1]);

                                    Log.d("22MarchV1", "loginStrings[" + i1 + "] ==> " + loginStrings[i1]);
                                }
                            } //if

                        }   //for


                        if (statusBool) {
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("No User", "No This User in mysql");
                        } else if (passwordString.equals(loginStrings[3])) {
                            Toast.makeText(getActivity(),"welcome  "+loginStrings[1],Toast.LENGTH_SHORT).show();
                        } else {
                            MyAlert myAlert = new MyAlert((getActivity()));
                            myAlert.myDialog("Password False","Please Try Again Password");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } //if


            }   // onClick
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);

        return view;
    }
}//Main Class
