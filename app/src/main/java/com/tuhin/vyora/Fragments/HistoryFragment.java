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
import com.tuhin.vyora.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private LineChart progressTrendChart;
    private LineChart cumulativeScoreChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        progressTrendChart = view.findViewById(R.id.progressTrendChart);
        cumulativeScoreChart = view.findViewById(R.id.cumulativeScoreChart);

        view.findViewById(R.id.btnMonth).setOnClickListener(v -> {});
        view.findViewById(R.id.btnWeek).setOnClickListener(v -> {});

        setupTrendChart();
        setupCumulativeChart();

        return view;
    }

    private void setupTrendChart() {
        if (getContext() == null) return;

        List<Entry> dailyEntries = new ArrayList<>();
        dailyEntries.add(new Entry(1, 60));
        dailyEntries.add(new Entry(2, 80));
        dailyEntries.add(new Entry(3, 50));
        dailyEntries.add(new Entry(4, 35));
        dailyEntries.add(new Entry(5, 75));
        dailyEntries.add(new Entry(6, 65));
        dailyEntries.add(new Entry(7, 70));
        dailyEntries.add(new Entry(8, 85));
        dailyEntries.add(new Entry(9, 45));
        dailyEntries.add(new Entry(10, 60));
        dailyEntries.add(new Entry(11, 75));
        dailyEntries.add(new Entry(12, 80));
        dailyEntries.add(new Entry(13, 70));

        LineDataSet dailySet = new LineDataSet(dailyEntries, "Daily");
        styleLineDataSet(dailySet, ContextCompat.getColor(getContext(), R.color.accent_blue));

        List<Entry> avgEntries = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            avgEntries.add(new Entry(i, 65));
        }
        LineDataSet avgSet = new LineDataSet(avgEntries, "Avg");
        styleLineDataSet(avgSet, ContextCompat.getColor(getContext(), R.color.accent_green));
        avgSet.setDrawCircles(false);
        avgSet.enableDashedLine(10f, 5f, 0f);

        LineData data = new LineData(dailySet, avgSet);
        progressTrendChart.setData(data);
        styleChart(progressTrendChart);
    }

    private void setupCumulativeChart() {
        if (getContext() == null) return;

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 50));
        entries.add(new Entry(2, 70));
        entries.add(new Entry(3, 100));
        entries.add(new Entry(4, 120));
        entries.add(new Entry(5, 150));
        entries.add(new Entry(6, 170));
        entries.add(new Entry(7, 160));
        entries.add(new Entry(8, 190));
        entries.add(new Entry(9, 210));
        entries.add(new Entry(10, 230));
        entries.add(new Entry(11, 260));
        entries.add(new Entry(12, 280));
        entries.add(new Entry(13, 300));

        LineDataSet dataSet = new LineDataSet(entries, "Score");
        int accentPurple = ContextCompat.getColor(getContext(), R.color.accent_purple);
        styleLineDataSet(dataSet, accentPurple);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(accentPurple);
        dataSet.setFillAlpha(30);

        LineData data = new LineData(dataSet);
        cumulativeScoreChart.setData(data);
        styleChart(cumulativeScoreChart);
    }

    private void styleLineDataSet(LineDataSet dataSet, int color) {
        if (getContext() == null) return;
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleHoleColor(ContextCompat.getColor(getContext(), R.color.card_bg));
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawValues(false);
    }

    private void styleChart(LineChart chart) {
        if (getContext() == null) return;
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setTouchEnabled(false);
        chart.setDrawGridBackground(false);

        int textSecondary = ContextCompat.getColor(getContext(), R.color.text_secondary);
        int itemBg = ContextCompat.getColor(getContext(), R.color.item_bg);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(textSecondary);
        xAxis.setGranularity(1f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(itemBg);
        leftAxis.setTextColor(textSecondary);
        leftAxis.setAxisMinimum(0f);

        chart.getAxisRight().setEnabled(false);
        chart.invalidate();
    }
}
