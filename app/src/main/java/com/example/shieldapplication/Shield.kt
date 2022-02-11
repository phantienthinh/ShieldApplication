package com.example.shieldapplication

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shieldapplication.ui.theme.ShieldApplicationTheme


const val PATH_SHIELD =
    "M215.865,31.266C181.852,25.51 149.016,15.829 117.488,1.57C115.264,0.523 113.17,0 110.946,0C108.853,0 106.629,0.523 104.405,1.57C73.008,15.829 40.172,25.51 6.158,31.266C1.841,32.051 0.01,33.621 0.01,38.461C-0.252,74.699 4.719,110.152 17.802,144.035C34.808,188.645 62.673,224.359 104.798,248.168C107.022,249.477 109.115,250 111.077,250C113.04,250 115.133,249.346 117.357,248.168C159.481,224.359 187.215,188.645 204.353,144.035C217.304,110.021 222.276,74.699 222.145,38.461C222.014,33.621 220.313,32.051 215.865,31.266ZM214.688,64.103C212.726,94.061 206.577,123.103 194.672,150.706C178.581,187.86 154.248,218.08 119.45,239.665C116.31,241.627 113.694,242.543 110.946,242.543C108.199,242.543 105.583,241.627 102.443,239.665C67.775,218.08 43.312,187.991 27.221,150.706C15.447,123.103 9.298,94.191 7.336,64.103C6.812,56.907 6.289,49.843 6.158,42.517C6.158,39.116 7.205,37.546 10.868,36.892C44.358,30.612 76.802,21.193 107.807,7.064C108.984,6.541 110.031,6.41 111.077,6.541C112.124,6.541 113.04,6.672 114.217,7.195C145.222,21.324 177.796,30.874 211.156,37.022C214.688,37.677 215.865,39.116 215.865,42.648C215.735,49.843 215.211,56.907 214.688,64.103Z"
const val PATH_SHADE_1 =
    "M63.589,172.945C97.472,172.029 126.383,161.433 145.745,131.606C150.847,123.756 154.772,115.253 157.519,106.226C158.696,102.171 160.659,100.601 165.106,101.909C174.002,104.395 183.029,105.049 192.317,105.31C196.504,105.441 198.858,107.011 197.55,111.851C185.253,159.994 161.313,200.287 119.057,228.283C113.432,232.076 109.115,232.207 103.228,228.413C83.474,215.331 67.514,198.586 54.039,179.355C52.992,177.786 50.899,176.216 51.815,174.253C52.731,172.029 55.216,173.207 57.048,173.076C59.141,172.814 61.365,172.945 63.589,172.945Z"
const val PATH_SHADE_2 =
    "M205.923,57.823C205.269,63.71 204.353,73.26 203.437,82.81C203.176,86.211 201.475,87.781 197.943,87.781C187.477,87.912 177.142,86.604 166.807,84.38C164.06,83.726 162.752,82.548 163.013,79.409C164.322,66.85 165.368,54.291 164.06,41.601C163.406,35.845 165.761,34.929 170.994,36.368C180.805,39.377 190.748,41.601 200.821,43.432C206.185,44.479 206.185,44.741 205.923,57.823Z"
const val PATH_BODY_SHIELD =
    "M1.35,33.472C36.586,30.497 88.82,9.918 110.533,0C160.162,25.414 212.272,32.232 219.716,33.472C222.818,184.096 127.283,243.602 110.533,248.561C-3.613,194.014 1.35,37.191 1.35,33.472Z"


@Composable
fun Shield(
    process: Float,
    scanColor: Int = Color(0xFF000000).hashCode(),
    repeatDuration: Int = 1000,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val percent by remember {
        mutableStateOf("${(process * 100).toInt()} %")
    }

    val paint by remember {
        mutableStateOf(
            Paint().apply {
                isAntiAlias = true
                textSize = 24f
                color = Color.White.hashCode()
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            })
    }

    val infinityTransition = rememberInfiniteTransition()

    val animationScale by infinityTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(repeatDuration),
            repeatMode = RepeatMode.Restart
        )
    )
    val shieldStroke by infinityTransition.animateFloat(
        initialValue = 5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(repeatDuration),
            repeatMode = RepeatMode.Restart
        )
    )

    val shieldStrokeAlpha by infinityTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(repeatDuration),
            repeatMode = RepeatMode.Restart
        )
    )

    val startAngle by infinityTransition.animateFloat(
        initialValue = -90f,
        targetValue = 270f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = repeatDuration),
            repeatMode = RepeatMode.Restart
        )
    )
    val sweepAngle by infinityTransition.animateFloat(
        initialValue = 0f,
        targetValue = 70f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = repeatDuration
            ),
            repeatMode = RepeatMode.Restart,
        ),

        )

    Canvas(
        modifier = modifier
            .background(color = Color(color = scanColor))
            .clipToBounds()
    ) {

        val pathShield = PathParser().parsePathString(PATH_SHIELD).toPath()
        val pathShieldChild = PathParser().parsePathString(PATH_SHADE_1).toPath()
        val pathShieldChild1 = PathParser().parsePathString(PATH_SHADE_2).toPath()
        val pathBodyShield = PathParser().parsePathString(PATH_BODY_SHIELD).toPath()

        val bound = pathShield.getBounds()
        val matrix = Matrix()
        val scale = (size.width / bound.width).coerceAtMost(size.height / bound.height) / 2f
        matrix.scale(scale, scale)

        val centerXShield = ((size.width) / 2f - (bound.width * scale) / 2f)
        val centerYShield = ((size.height) / 2f - (bound.height * scale) / 2f)

        withTransform(transformBlock = {
            translate(
                left = centerXShield,
                top = centerYShield
            )
            transform(matrix)

        }) {

            val eccentric = (bound.height - bound.left) + 50

            clipPath(path = pathBodyShield) {
                drawRect(Color(0x33FFFFFF))
                drawArc(
                    color = Color(0x4DFFFFFF),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(bound.top - eccentric / 2, bound.left - eccentric / 2),
                    size = Size(bound.right + eccentric, bound.bottom + eccentric),
                )
            }

            drawPath(path = pathShield, color = Color.White)
            drawPath(path = pathShieldChild, color = Color.White, alpha = 0.3f)
            drawPath(path = pathShieldChild1, color = Color.White, alpha = 0.3f)



            drawPercent(percent = percent, bound = bound, paint = paint)


            scale(animationScale, pivot = bound.center) {
                drawPath(
                    path = pathBodyShield,
                    color = Color.White,
                    style = Stroke(shieldStroke),
                    alpha = shieldStrokeAlpha
                )
            }

        }
    }
}

private fun DrawScope.drawPercent(
    percent: String,
    bound: Rect,
    paint: Paint
) {
    val rect = android.graphics.Rect()
    paint.getTextBounds(percent, 0, percent.length, rect)
    val widthText = rect.width()
    val heightText = rect.height()

    val xText = bound.width / 2f - widthText / 2f
    val yText = bound.height / 2f + heightText / 2f

    drawIntoCanvas {
        it.nativeCanvas.drawText(percent, xText, yText, paint)
    }
}

@Preview()
@Composable
private fun Preview() {
    ShieldApplicationTheme {
        Shield(
            process = 0.2f, scanColor = Color.Yellow.hashCode(), repeatDuration = 1000,
            modifier = Modifier.size(
                width = 250.dp,
                height = 150.dp
            )
        )
    }
}