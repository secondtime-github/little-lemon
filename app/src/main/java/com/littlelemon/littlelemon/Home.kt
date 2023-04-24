package com.littlelemon.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Home(navController: NavHostController, databaseMenuItems: List<MenuItemRoom>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(navController = navController)
        Hero()
        MenuItems(items = databaseMenuItems)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    val databaseMenuItems: List<MenuItemRoom> = listOf(
        MenuItemRoom(
            1,
            "Greek Salad",
            "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            "starters"
        ),
        MenuItemRoom(
            2,
            "Lemon Desert",
            "Traditional homemade Italian Lemon Ricotta Cake.",
            10.0,
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/images/pasta.jpg",
            "desserts"
        ),
    )
    Home(navController = navController, databaseMenuItems = databaseMenuItems)
}

@Composable
fun Header(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(modifier = Modifier.size(50.dp)) { }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little lemon Logo",
            modifier = Modifier.size(200.dp, 100.dp)
        )
        IconButton(
            onClick = { navController.navigate(Profile.route) },
            modifier = Modifier.size(50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green)
            .padding(horizontal = 24.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Chicago",
                    fontSize = 24.sp,
                    color = LittleLemonColor.cloud
                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    color = LittleLemonColor.cloud,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(0.6f),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(120.dp, 120.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Enter search phrase") },
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn {
        itemsIndexed(items) {_, item ->
            MenuItem(item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = item.title,
                color = LittleLemonColor.charcoal,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = item.description,
                color = LittleLemonColor.charcoal.copy(alpha = 0.5f),
                fontSize = 14.sp,
                lineHeight = 16.sp,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(0.75f),
            )
            Text(
                text = "$${item.price}",
                color = LittleLemonColor.charcoal.copy(alpha = 0.7f),
                fontWeight = FontWeight.Bold,
            )
        }

        GlideImage(
            model = item.image,
            contentDescription = "dish_${item.id}",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(75.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
    Divider(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow
    )
}