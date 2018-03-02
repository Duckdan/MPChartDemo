package com.study.mpchartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BarChart bc;
    private Random random;
    private LineChart lc;
    ArrayList<BarEntry> barYVals = new ArrayList<>();//柱状Y轴方向第三组数组
    ArrayList<Entry> lineYVals = new ArrayList<>();//柱状Y轴方向第三组数组
    ArrayList<String> xVals = new ArrayList<>();//X轴数据


    public static final int[] LINE_COLORS = {
            Color.rgb(140, 210, 118), Color.rgb(159, 143, 186), Color.rgb(233, 197, 23)
    };//绿色，紫色，黄色

    public static final int[] LINE_FILL_COLORS = {
            Color.rgb(222, 239, 228), Color.rgb(246, 234, 208), Color.rgb(235, 228, 248)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bc = (BarChart) findViewById(R.id.bc);
        lc = (LineChart) findViewById(R.id.lc);
        random = new Random();


        for (int i = 0; i < 250; i++) {//添加数据源
            xVals.add((i + 1) + "新消息呵呵呵月");
            barYVals.add(new BarEntry(random.nextInt(500), i));
        }

        for (int i = 0; i < 250; i++) {//添加数据源
            lineYVals.add(new Entry(random.nextInt(10), i));
        }


        initBarChart();
        initLineChart();
    }

    /**
     * 条形图
     */
    private void initBarChart() {
        BarDataSet barDataSet = new BarDataSet(barYVals, "");
        barDataSet.setColor(Color.parseColor("#E7A37F"));//设置第一组数据颜色

        BarData bardata = new BarData(xVals, barDataSet);
        bc.setData(bardata);
        //设置自定义的markerView
        MPChartMarkerView markerView = new MPChartMarkerView(this, R.layout.custom_marker_view);
        bc.setMarkerView(markerView);

        bc.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        bc.getXAxis().setDrawGridLines(false);//不显示网格

        bc.getAxisRight().setEnabled(false);//右侧不显示Y轴
        bc.getAxisLeft().setAxisMinValue(0.0f);//设置Y轴显示最小值，不然0下面会有空隙
        bc.getAxisLeft().setDrawGridLines(true);//不设置Y轴网格

        bc.setBackgroundColor(Color.parseColor("#ECECEC"));

        bc.setDescription("");//设置描述
        bc.setDescriptionTextSize(20.f);//设置描述字体
        bc.animateXY(1000, 2000);//设置动画


    }

    /**
     * 折线图
     */
    private void initLineChart() {
        LineDataSet lineDataSet = new LineDataSet(lineYVals, "红细胞数目(单位：x10^12/L)");
        lineDataSet.setColor(Color.rgb(140, 210, 118));
        lineDataSet.setDrawCircleHole(false);//不绘制空心圆


        LineData lineData = new LineData(xVals, lineDataSet);
        lineData.setDrawValues(false);
        lc.setData(lineData);
        lc.setScaleEnabled(true); //打开缩放的控制，mScaleXEnabled和mScaleYEnabled默认值为true
        lc.setTouchEnabled(true); //打开触控的控制，mTouchEnabled默认值为true
        lc.setDragEnabled(true);  //打开拖拽的控制，mDragEnabled默认值为true,但是其不影响缩放
        lc.setMaxVisibleValueCount(6);
        lc.setBackgroundColor(Color.parseColor("#ffff00"));
        lc.setDescription("");//设置描述
        lc.setDescriptionTextSize(20.f);//设置描述字体
        lc.animateXY(1000, 2000);//设置动画
        lc.setExtraOffsets(20, 20, 20, 20); //设置控件的内部间距


        Legend legend = lc.getLegend(); //图例，即控件左下标的图例描述
        legend.setTextColor(Color.RED); //设置图例文本的颜色
        legend.setForm(Legend.LegendForm.LINE); //设置图例的样式
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT); //设置图例的位置


        XAxis axis = lc.getXAxis();
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        axis.setDrawGridLines(false);//不显示网格
        axis.setAvoidFirstLastClipping(true);  //设置为true时，避免x轴坐标的第一个和最后一个被裁剪以至于显示不全
        axis.setDrawLabels(true); //是否绘制标签，默认为true
        axis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
                return original + ":" + index;
            }
        });
        axis.setTextColor(Color.RED);
//        axis.setLabelsToSkip(0);
//        axis.setSpaceBetweenLabels(6);
        axis.setLabelRotationAngle(-30); //x轴标签旋转30度

        YAxis rightYAxis = lc.getAxisRight();
        rightYAxis.setEnabled(false);//右侧不显示Y轴
        YAxis leftYAxis = lc.getAxisLeft();
        leftYAxis.setDrawLabels(false);//是否绘制标签，默认为true
        leftYAxis.setAxisMinValue(0.0f);//设置Y轴显示最小值，不然0下面会有空隙
        leftYAxis.setDrawGridLines(false);//不设置Y轴网格

    }


}
