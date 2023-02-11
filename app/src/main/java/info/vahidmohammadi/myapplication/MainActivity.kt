package info.vahidmohammadi.myapplication

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener

class MainActivity : AppCompatActivity() {

    lateinit var candleStickChart: CombinedChart
    lateinit var lineChart: CombinedChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        candleStickChart = findViewById(R.id.chart)
        lineChart = findViewById(R.id.chart2)

        setupChart()

        candleStickChart.onChartGestureListener = object : OnChartGestureListener {
            override fun onChartGestureEnd(
                me: MotionEvent,
                lastPerformedGesture: ChartTouchListener.ChartGesture,
            ) {
                linkCharts()
            }

            override fun onChartGestureStart(
                me: MotionEvent,
                lastPerformedGesture: ChartTouchListener.ChartGesture,
            ) {
                linkCharts()
            }

            override fun onChartLongPressed(me: MotionEvent) {
                linkCharts()
            }

            override fun onChartDoubleTapped(me: MotionEvent) {
                linkCharts()
            }

            override fun onChartSingleTapped(me: MotionEvent) {
                linkCharts()
            }

            override fun onChartFling(
                me1: MotionEvent,
                me2: MotionEvent,
                velocityX: Float,
                velocityY: Float,
            ) {
                linkCharts()
            }

            override fun onChartScale(me: MotionEvent, scaleX: Float, scaleY: Float) {
                linkCharts()
            }

            override fun onChartTranslate(me: MotionEvent, dX: Float, dY: Float) {
                linkCharts()
            }
        }

        lineChart.onChartGestureListener = object : OnChartGestureListener {
            override fun onChartGestureEnd(
                me: MotionEvent,
                lastPerformedGesture: ChartTouchListener.ChartGesture,
            ) {
                linkCharts()
            }


            override fun onChartGestureStart(
                me: MotionEvent,
                lastPerformedGesture: ChartTouchListener.ChartGesture,
            ) {
                linkCharts()
            }

            override fun onChartLongPressed(me: MotionEvent) {
                linkCharts()
            }

            override fun onChartDoubleTapped(me: MotionEvent) {
                linkCharts()
            }

            override fun onChartSingleTapped(me: MotionEvent) {
                linkCharts()
            }

            override fun onChartFling(
                me1: MotionEvent,
                me2: MotionEvent,
                velocityX: Float,
                velocityY: Float,
            ) {
                linkCharts()
            }

            override fun onChartScale(me: MotionEvent, scaleX: Float, scaleY: Float) {
                linkCharts()
            }

            override fun onChartTranslate(me: MotionEvent, dX: Float, dY: Float) {
                linkCharts()
            }
        }
    }

    fun linkCharts() {
        val masterMatrix = candleStickChart.viewPortHandler.matrixTouch
        val masterValue = FloatArray(9) {
            0f
        }
        masterMatrix.getValues(masterValue)

        val followValue = FloatArray(9) {
            0f
        }

        lineChart.viewPortHandler.matrixTouch.getValues(followValue)

        masterValue.forEachIndexed { index, fl ->
            followValue[index] = fl
        }

        lineChart.viewPortHandler.matrixTouch.setValues(followValue)

        lineChart.viewPortHandler.refresh(lineChart.viewPortHandler.matrixTouch, lineChart, true)
    }

    private fun setupChart() {
        candleStickChart.description.isEnabled = false
        candleStickChart.setMaxVisibleValueCount(60)
        candleStickChart.setPinchZoom(false)
        candleStickChart.setDrawGridBackground(false)
        candleStickChart.data = generateCombinedData1()
        candleStickChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        candleStickChart.xAxis.setDrawGridLines(false)
        candleStickChart.xAxis.granularity = 1f
        candleStickChart.axisLeft.setDrawGridLines(false)
        candleStickChart.axisRight.isEnabled = false
        candleStickChart.legend.isEnabled = false
        candleStickChart.xAxis.axisMinimum = 0f
        candleStickChart.xAxis.setAvoidFirstLastClipping(true)
        candleStickChart.setVisibleXRangeMaximum(5f)
        candleStickChart.moveViewToX(0f)

        lineChart.data = generateCombinedData2()
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.granularity = 1f
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisRight.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.xAxis.axisMinimum = 0f
        lineChart.xAxis.setAvoidFirstLastClipping(true)
        lineChart.setVisibleXRangeMaximum(5f)
        lineChart.moveViewToX(0f)
    }

    fun generateCombinedData1(): CombinedData {
        val combinedData = CombinedData()
        combinedData.setData(generateCandleStickData())
        return combinedData
    }

    fun generateCombinedData2(): CombinedData {
        val combinedData = CombinedData()
        combinedData.setData(generateLineData())
        return combinedData
    }

    private fun generateCandleStickData(): CandleData {
        val candleDataSet = CandleDataSet(generateCandleStickEntries(), "Price")
        candleDataSet.setDrawIcons(false)
        candleDataSet.axisDependency = YAxis.AxisDependency.LEFT
        candleDataSet.shadowColor = Color.DKGRAY
        candleDataSet.shadowWidth = 0.7f
        candleDataSet.decreasingColor = Color.RED
        candleDataSet.decreasingPaintStyle = Paint.Style.FILL
        candleDataSet.increasingColor = Color.rgb(122, 242, 84)
        candleDataSet.increasingPaintStyle = Paint.Style.STROKE
        candleDataSet.neutralColor = Color.BLUE
        candleDataSet.setDrawValues(false)
        return CandleData(candleDataSet)
    }

    private fun generateLineData(): LineData {
        val lineDataSet = LineDataSet(generateLineEntries(), "RSI")
        lineDataSet.color = Color.CYAN
        lineDataSet.lineWidth = 1.5f
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.axisDependency = YAxis.AxisDependency.RIGHT
        return LineData(lineDataSet)
    }

    private fun generateCandleStickEntries(): List<CandleEntry> {
        return listOf(
            CandleEntry(1f, 8f, 4f, 6f, 5f),
            CandleEntry(2f, 6f, 3f, 5f, 4f),
            CandleEntry(3f, 12f, 7f, 9f, 8f),
            CandleEntry(4f, 15f, 10f, 14f, 11f),
            CandleEntry(5f, 10f, 5f, 7f, 6f),
            CandleEntry(6f, 8f, 4f, 6f, 5f),
            CandleEntry(7f, 6f, 3f, 5f, 4f),
            CandleEntry(8f, 12f, 7f, 9f, 8f),
            CandleEntry(9f, 15f, 10f, 14f, 11f),
            CandleEntry(10f, 10f, 5f, 7f, 6f),
            CandleEntry(11f, 8f, 4f, 6f, 5f),
            CandleEntry(12f, 6f, 3f, 5f, 4f),
            CandleEntry(13f, 12f, 7f, 9f, 8f),
            CandleEntry(14f, 20f, 10f, 14f, 11f),
        )
    }

    private fun generateLineEntries(): List<Entry> {
        return listOf(
            Entry(1f, 70f),
            Entry(2f, 60f),
            Entry(3f, 55f),
            Entry(4f, 65f),
            Entry(5f, 80f),
            Entry(6f, 70f),
            Entry(7f, 60f),
            Entry(8f, 55f),
            Entry(9f, 65f),
            Entry(10f, 80f),
            Entry(11f, 70f),
            Entry(12f, 200f),
            Entry(13f, 55f),
            Entry(14f, 65f),
        )
    }
}
