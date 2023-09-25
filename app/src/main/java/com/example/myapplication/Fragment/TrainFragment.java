package com.example.myapplication.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ChooseDateActivity;
import com.example.myapplication.CitiesActivity;
import com.example.myapplication.DetailActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils;

public class TrainFragment extends Fragment {
    private static final int ORIGIN_REQUEST_CODE = 86;
    private static final int DESTINATION_REQUEST_CODE = 90;
    private static final int REQUEST_DATE_CODE = 95;
    View view;
    TextView txtOrigin,txtDestination,txtDate,txtPassengers;
    Button btnSearch;

    private PersianDatePickerDialog picker;

    private static final String TAG = "MainActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.train_fragment,container,false);
        setupViews();
        return view;
    }

    private void setupViews() {
        txtOrigin=view.findViewById(R.id.txt_trainFragment_origin);
        txtDestination=view.findViewById(R.id.txt_trainFragment_destination);
        txtDate=view.findViewById(R.id.txt_trainFragment_date);
        txtPassengers=view.findViewById(R.id.txt_trainFragment_passenger);
        btnSearch=view.findViewById(R.id.btn_trainFragment_search);
        txtOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent,ORIGIN_REQUEST_CODE);
            }
        });
        txtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent,DESTINATION_REQUEST_CODE);
            }
        });
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new PersianDatePickerDialog(getContext())
                        .setPositiveButtonString("باشه")
                        .setNegativeButton("بیخیال")
                        .setTodayButton("امروز")
                        .setTodayButtonVisible(true)
                        .setMinYear(1400)
                        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                        .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
                        .setMaxDay(PersianDatePickerDialog.THIS_DAY)
                        .setInitDate(1370, 3, 13)
                        .setActionTextColor(Color.GRAY)
                        //.setTypeFace(typeface)
                        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                        .setShowInBottomSheet(true)
                        .setListener(new PersianPickerListener() {
                            @Override
                            public void onDateSelected(@NotNull PersianPickerDate persianPickerDate) {
                                Log.d(TAG, "onDateSelected: " + persianPickerDate.getTimestamp());//675930448000
                                Log.d(TAG, "onDateSelected: " + persianPickerDate.getGregorianDate());//Mon Jun 03 10:57:28 GMT+04:30 1991
                                Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianLongDate());// دوشنبه  13  خرداد  1370
                                Log.d(TAG, "onDateSelected: " + persianPickerDate.getPersianMonthName());//خرداد
                                Log.d(TAG, "onDateSelected: " + PersianCalendarUtils.isPersianLeapYear(persianPickerDate.getPersianYear()));//true
                                txtDate.setText(persianPickerDate.getPersianDay() + " " + persianPickerDate.getPersianMonthName() + " " + persianPickerDate.getPersianYear());
                                Toast.makeText(getContext(), persianPickerDate.getPersianDay() + " " + persianPickerDate.getPersianMonthName() + " " + persianPickerDate.getPersianYear(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
            }
        });

        txtPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassengersDialog passengersDialog=new PassengersDialog();
                passengersDialog.show(MainActivity.fragmentManager,null);
                passengersDialog.setOnSubmitButtonClicked(new PassengersDialog.OnSubmitButtonClicked() {
                    @Override
                    public void onSubmitted(String count) {
                        txtPassengers.setText(count+" نفر ");
                    }
                });
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DetailActivity.class);
                intent.putExtra("type","train");
                intent.putExtra("origin",txtOrigin.getText().toString());
                intent.putExtra("destination",txtDestination.getText().toString());
                intent.putExtra("date",txtDate.getText().toString());
                intent.putExtra("passengers",txtPassengers.getText().toString());

                startActivity(intent);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ORIGIN_REQUEST_CODE && resultCode==-1 && data!=null){
            txtOrigin.setText(data.getExtras().getString("city"));
        }else if(requestCode==DESTINATION_REQUEST_CODE && resultCode==-1 && data!=null){
            txtDestination.setText(data.getExtras().getString("city"));
        }else if (requestCode == REQUEST_DATE_CODE && resultCode == -1 && data != null) {
            String monthName="";
            switch (data.getExtras().getInt("month")) {
                case 0:
                    monthName = "فروردین";
                    break;
                case 1:
                    monthName = "اردیبهشت";
                    break;
                case 2:
                    monthName = "خرداد";
                    break;
                case 3:
                    monthName = "تیر";
                    break;
                case 4:
                    monthName = "مرداد";
                    break;
                case 5:
                    monthName = "شهریور";
                    break;
                case 6:
                    monthName = "مهر";
                    break;
                case 7:
                    monthName = "آبان";
                    break;
                case 8:
                    monthName = "آذر";
                    break;
                case 9:
                    monthName = "دی";
                    break;
                case 10:
                    monthName = "بهمن";
                    break;
                case 11:
                    monthName = "اسفند";
                    break;
            }
            txtDate.setText(data.getExtras().getInt("day")+" "+monthName+" "+data.getExtras().getInt("year"));
        }
    }
}
