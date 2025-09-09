package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Data class للـ Repository
data class Repository(
    val name: String,
    val owner: String,
    val description: String,
    val stars: Int,
    val logo: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // هنا عشان يمنع التحذير
@Composable
fun AppContent() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Github Repositories") }
            )
        }
    ) { padding ->
        val repoList = listOf(
            Repository("less.js", "less", "Less. The dynamic stylesheet language.", 16971, R.drawable.less),
            Repository("ruby", "ruby", "The Ruby Programming Language", 20981, R.drawable.ruby),
            Repository("rust", "rust-lang", "Empowering everyone to build reliable and efficient software.", 87181, R.drawable.rust),
            Repository("julia", "JuliaLang", "The Julia Programming Language", 43541, R.drawable.julia),
            Repository("language", "tolmasky", "A fast PEG parser written in JavaScript with first class errors", 411, R.drawable.language),
            Repository("kotlin", "JetBrains", "The Kotlin Programming Language", 46289, R.drawable.kotlin)
        )
        RepositoriesList(repoList, Modifier.padding(padding))
    }
}

@Composable
fun RepositoriesList(repos: List<Repository>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(repos) { repo ->
            RepositoryItem(repo)
        }
    }
}

@Composable
fun RepositoryItem(repo: Repository) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = repo.logo),
                contentDescription = repo.name,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = repo.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = repo.owner, fontSize = 14.sp, color = Color.Gray)
                Text(text = repo.description, fontSize = 14.sp)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = repo.stars.toString(), fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Stars",
                    tint = Color(0xFFFFC107)
                )
            }
        }
    }
}
