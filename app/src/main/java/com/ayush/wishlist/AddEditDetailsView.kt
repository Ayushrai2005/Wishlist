package com.ayush.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ayush.wishlist.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id : Long ,
    viewModel: WishViewModel,
    navController: NavController
){
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if(id!= 0L){
        //entry through an existing list with data
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L , "" , ""))
        viewModel.wishTitleState= wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        //We get through creating an new entry
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                        title =
                 if(id!= 0L) stringResource(id = R.string.update_wish)
                 else stringResource(id = R.string.add_wish) ){navController.navigateUp()}
                 },
                    scaffoldState = scaffoldState


    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                viewModel.onWishTitleChanged(it)
            })

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                })

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()){


                    if(id!=0L){
                        viewModel.updateWish(Wish(
                            id = id ,
                            title = viewModel.wishTitleState.trim(),
                            description = viewModel.wishDescriptionState.trim()
                        ))
                    }else{
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim())
                        )
                        snackMessage.value ="\uD83E\uDDD9 May your wish be fulfilled"
                    }
                }else{
                    snackMessage.value = "Enter Fields to create a Wish"
                }

                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }) {
                Text(
                        text = if (id != 0L) stringResource(id = R.string.update_wish)
                        else stringResource(
                            id = R.string.add_wish
                        ),
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }

            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label : String ,
    value : String ,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(value = value, onValueChange = onValueChanged,
    label = { Text(text = label , color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //Using PreDefined Color
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.lavender1),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.lavender1),
            unfocusedLabelColor = colorResource(id = R.color.black),

        )

    )

}