package com.tuhin.vyora;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LineChart weeklyChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        weeklyChart = findViewById(R.id.weeklyChart);
        setupChart();
    }

    private void setupChart() {
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 55));
        entries.add(new Entry(1, 78));
        entries.add(new Entry(2, 45));
        entries.add(new Entry(3, 90));
        entries.add(new Entry(4, 65));
        entries.add(new Entry(5, 75));
        entries.add(new Entry(6, 75));

        LineDataSet dataSet = new LineDataSet(entries, "Progress");
        dataSet.setColor(Color.parseColor("#9B8AFB"));
        dataSet.setCircleColor(Color.parseColor("#9B8AFB"));
        dataSet.setLineWidth(3f);
        dataSet.setCircleRadius(5f);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleHoleColor(Color.parseColor("#1B1C22"));
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawValues(false);
        dataSet.setDrawFilled(false);

        LineData lineData = new LineData(dataSet);
        weeklyChart.setData(lineData);

        // Customize Chart
        weeklyChart.getDescription().setEnabled(false);
        weeklyChart.getLegend().setEnabled(false);
        weeklyChart.setTouchEnabled(false);
        weeklyChart.setDragEnabled(false);
        weeklyChart.setScaleEnabled(false);
        weeklyChart.setPinchZoom(false);
        weeklyChart.setDrawGridBackground(false);

        XAxis xAxis = weeklyChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.parseColor("#8F8F8F"));
        xAxis.setGranularity(1f);
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));

        YAxis leftAxis = weeklyChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.parseColor("#2A2C32"));
        leftAxis.setTextColor(Color.parseColor("#8F8F8F"));
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setLabelCount(6);

        weeklyChart.getAxisRight().setEnabled(false);
        weeklyChart.invalidate();
    }
}