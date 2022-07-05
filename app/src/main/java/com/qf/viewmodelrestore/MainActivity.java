package com.qf.viewmodelrestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.qf.viewmodelrestore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    ActivityMainBinding binding;
    final static String KEY_NUMBER = "my_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        if (savedInstanceState != null) {
            myViewModel.getNumber().setValue(savedInstanceState.getInt(KEY_NUMBER));
        }

        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    /**
     * Activity的onSaveInstanceState()方法了，它不是生命周期方法，它不同于生命周期方法，它并不会一定会被触发，它只有具备以下条件的时候才会触发
     * ● 当按下HOME键的时
     * ● 长按HOME键，选择运行程序的时
     * ● 按下电源(关闭屏幕显示)时
     * ● 从Activity中启动其他Activity时
     * ● 屏幕方向切换时(例如从竖屏切换到横屏时)
     * onSaveInstanceState()方法携带了一个Bundle类型参数提供了一系列方法用于保存数据
     */
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER, myViewModel.getNumber().getValue());
    }
}