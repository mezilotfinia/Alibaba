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
import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils;

public class InsideFlightFragment extends Fragment {
    View view;
    Button btnSearch;
    TextView edtSource, edtDestination, edtDate;
    private static final int REQUEST_ORIGIN_CODE = 185;
    private static final int REQUEST_DESTINATION_CODE = 195;
    private static final int REQUEST_Date_CODE = 175;

    private PersianDatePickerDialog picker;

    private static final String TAG = "MainActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inside_filght_fragment, container, false);
        btnSearch = view.findViewById(R.id.btn_insideFlight_search);
        edtSource = (TextView) view.findViewById(R.id.edt_insideFlight_source);
        edtDestination = (TextView) view.findViewById(R.id.edt_insideFlight_destination);
        edtDate = (TextView) view.findViewById(R.id.edt_insideFlight_date);

        edtSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent, REQUEST_ORIGIN_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation, R.anim.out_animation);
            }
        });

        edtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent, REQUEST_DESTINATION_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation, R.anim.out_animation);
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
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
                                edtDate.setText(persianPickerDate.getPersianDay() + " " + persianPickerDate.getPersianMonthName() + " " + persianPickerDate.getPersianYear());
                                Toast.makeText(getContext(), persianPickerDate.getPersianDay() + " " + persianPickerDate.getPersianMonthName() + " " + persianPickerDate.getPersianYear(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDismissed() {

                            }
                        });

                picker.show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DetailActivity.class);
                intent.putExtra("type","flight");
                intent.putExtra("origin",edtSource.getText().toString());
                intent.putExtra("destination",edtDestination.getText().toString());
                intent.putExtra("date",edtDate.getText().toString());
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_ORIGIN_CODE && resultCode == -1 && data != null) {
            edtSource.setText(data.getExtras().getString("city"));
        } else if (requestCode == REQUEST_DESTINATION_CODE && resultCode == -1 && data != null) {
            edtDestination.setText(data.getExtras().getString("city"));
        } else if (requestCode == REQUEST_Date_CODE && resultCode == -1 && data != null) {
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
            edtDate.setText(data.getExtras().getInt("day")+" "+monthName+" "+data.getExtras().getInt("year"));
        }
    }

}
