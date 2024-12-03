package com.oganbelema.samplekmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.oganbelema.samplekmp.data.Product
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = HomeViewModel()
        val data = viewModel.products.collectAsState()
        AppContent(data)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(data: State<List<Product>>) {
    BoxWithConstraints {
        val columns = 2
        val scrollState = rememberLazyStaggeredGridState()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(columns),
                state = scrollState,
                contentPadding = PaddingValues(16.dp)){

                val searchHint = "Search Products"

                item(span = StaggeredGridItemSpan.FullLine){
                    Column {
                        SearchBar(
                            modifier = Modifier.fillMaxWidth(),
                            inputField = {
                                SearchBarDefaults.InputField(
                                    onSearch = { },
                                    expanded = false,
                                    onExpandedChange = {},
                                    placeholder = { Text(searchHint) },
                                    query = "",
                                    onQueryChange = {},
                                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = searchHint) },
                                )
                            },
                            expanded = false,
                            onExpandedChange = {},
                            shape = RoundedCornerShape(8.dp)
                        ){}
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                items(items = data.value) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        elevation = 4.dp) {
                        Column {
                            Image(
                                painter = rememberImagePainter(it.image),
                                contentDescription = it.title,
                                modifier = Modifier.heightIn(130.dp)
                                    .padding(8.dp)
                            )
                            Text(
                                text = it.title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "€${it.price}",
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
