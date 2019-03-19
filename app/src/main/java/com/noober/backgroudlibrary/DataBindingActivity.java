//package com.noober.backgroudlibrary;
//
//import androidx.databinding.DataBindingUtil;
//import androidx.databinding.ViewDataBinding;
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//
//import com.noober.backgroudlibrary.databinding.ActivityDataBindingBinding;
//import com.noober.background.BackgroundLibrary;
//
//public class DataBindingActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        BackgroundLibrary.inject(this);
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_data_binding);
//        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
//        User user = new User();
//        user.setName("xxx");
//        binding.setUser(user);
//    }
//}
