package com.mobile.app.sporting

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.app.sporting.ui.*
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable fun SelectionDetails(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
                modifier = Modifier.fillMaxSize()
                        .background(color = surfaceColor)
        ) {
            val (
                    headerRef,
                    buttonRef,
                    gapRef,
                    itemsRef) = createRefs()

            Header(modifier = Modifier.constrainAs(headerRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, navController = navController)

            BottomButton(modifier = Modifier.constrainAs(buttonRef) {
                bottom.linkTo(parent.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            Spacer(modifier = Modifier.height(80.dp).width(6.dp).background(Color.Blue).constrainAs(gapRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(headerRef.bottom)
            })

            Column(
                    modifier = Modifier.fillMaxWidth()
                            .constrainAs(itemsRef) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(gapRef.bottom)
                                bottom.linkTo(buttonRef.top, margin = 24.dp)
                            }
            ) {
                mockItems.forEach {
                    ItemRow(data = it, isHighlighted = it.row == 2)
                }
            }
        }

        GraphCard(modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(0.85f)
                .offset(y = 200.dp)
                .height(180.dp)
        )
    }
}

@Composable fun GraphCard(
        modifier: Modifier = Modifier
) {
    Surface(
            shape = RoundedCornerShape(16.dp),
            color = graphCardBg,
            elevation = 4.dp,
            modifier = modifier
    ) {

    }
}

@Composable fun BottomButton(
        modifier: Modifier = Modifier
) {
    Box(
            modifier = modifier
                    .clickable(onClick = {})
                    .background(color = seeMoreBg, shape = RoundedCornerShape(32.dp))
                    .fillMaxWidth(0.6f)
                    .padding(start = 24.dp, end = 8.dp)
                    .padding(vertical = 16.dp)
    ) {
        Text(
                text = "See more",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
        )
        Box(modifier = Modifier.size(32.dp)
                .align(Alignment.CenterEnd)
                .background(Color.White, shape = CircleShape)) {
            Icon(
                    Icons.Default.KeyboardArrowRight,
                    tint = seeMoreBg,
                    modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable fun Header(
        modifier: Modifier = Modifier,
        navController: NavController
) {
    val imgUrl = "https://i.pravatar.cc/200?img=30"

    Box(modifier = modifier
            .height(300.dp)
            .background(
                    color = selectionHeaderBgColor,
                    shape = RoundedCornerShape(bottomRight = 32.dp, bottomLeft = 32.dp))
    ) {
        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 36.dp)) {
            Icon(Icons.Default.ArrowBack, tint = Color.White)
        }

        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .padding(horizontal = 16.dp),
        ) {
            Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                        modifier = Modifier.size(84.dp)
                                .background(imageBgColor, shape = CircleShape)
                ) {
                    CoilImage(
                            data = imgUrl,
                            modifier = Modifier.size(78.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.Center)
                    )

                    Box(
                            modifier = Modifier
                                    .offset(x=-2.dp, y = -2.dp)
                                    .size(24.dp)
                                    .background(color = Color.White, shape = CircleShape)
                                    .align(Alignment.BottomEnd)

                    ) {
                        Text(
                                text = "23",
                                color = headerCounterTextColor,
                                modifier = Modifier.align(Alignment.Center),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                        text = "Gabriella Estrada",
                        color = selectionHeaderText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                    text = "Rent Surfing",
                    color = selectionHeaderText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable fun ItemRow(
        data: ItemRowData,
        isHighlighted: Boolean,
        modifier: Modifier = Modifier
) {
    Surface(
            color = if (isHighlighted) graphCardBg else surfaceColor,
            shape = if (isHighlighted) RoundedCornerShape(topRight = 56.dp) else RectangleShape,
            modifier = modifier.fillMaxWidth()
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).padding(end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            RowNumber(isHighlighted = isHighlighted, data.row)
            Column(horizontalAlignment = Alignment.Start) {

                Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = data.country, color = textColorOne)
                    Icon(Icons.Default.Star,
                            tint = if (isHighlighted) selectedIconColor else unselectedColor,
                    )
                }
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = data.title, color = textColorTwo, fontSize = 18.sp, fontWeight = FontWeight.W500)
                Spacer(modifier = Modifier.size(2.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = data.price, color = textColorOne)
                    Text(
                            text = "more",
                            color = if (isHighlighted) selectedColor else unselectedColor,
                            textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

@Composable fun RowNumber(isHighlighted: Boolean, row: Int) {
    Box(
            modifier = Modifier
                    .size(32.dp)
                    .background(
                            color = if (isHighlighted) selectedColor else unselectedColor,
                            shape = if (isHighlighted) RoundedCornerShape(16.dp) else CircleShape
                    )
    ) {
        Text(text = "$row", color = rowNumberTextColor, modifier = Modifier.align(Alignment.Center))
    }
}

data class ItemRowData(
        val row: Int,
        val country: String,
        val title: String,
        val price: String
)

val mockItems = listOf(
        ItemRowData(1,"Australia", "Rent Surfing on Sydney's", "from $61.00"),
        ItemRowData(2,"USA", "Rent Surfing on California", "from $72.00")
)