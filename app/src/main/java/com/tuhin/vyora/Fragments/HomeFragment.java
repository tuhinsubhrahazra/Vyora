package com.tuhin.vyora.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.tuhin.vyora.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private LineChart weeklyChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        weeklyChart = view.findViewById(R.id.weeklyChart);
        setupChart();

        return view;
    }

    private void setupChart() {
        if (getContext() == null) return;

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 55));
        entries.add(new Entry(1, 78));
        entries.add(new Entry(2, 45));
        entries.add(new Entry(3, 90));
        entries.add(new Entry(4, 65));
        entries.add(new Entry(5, 75));
        entries.add(new Entry(6, 75));

        int accentPurple = ContextCompat.getColor(getContext(), R.color.accent_purple);
        int cardBg = ContextCompat.getColor(getContext(), R.color.card_bg);
        int textSecondary = ContextCompat.getColor(getContext(), R.color.text_secondary);
        int itemBg = ContextCompat.getColor(getContext(), R.color.item_bg);

        LineDataSet dataSet = new LineDataSet(entries, "Progress");
        dataSet.setColor(accentPurple);
        dataSet.setCircleColor(accentPurple);
        dataSet.setLineWidth(3f);
        dataSet.setCircleRadius(5f);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleHoleColor(cardBg);
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
        xAxis.setTextColor(textSecondary);
        xAxis.setGranularity(1f);
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));

        YAxis leftAxis = weeklyChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(itemBg);
        leftAxis.setTextColor(textSecondary);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setLabelCount(6);

        weeklyChart.getAxisRight().setEnabled(false);
        weeklyChart.invalidate();
    }
}
