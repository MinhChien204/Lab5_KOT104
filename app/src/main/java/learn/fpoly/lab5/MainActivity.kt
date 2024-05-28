package learn.fpoly.lab5

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoginApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(
                containerColor =
                Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation =
                4.dp
            )
        ) {
            LoginScreen()
        }
    }
}

@Composable
fun DialogComponent(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogMessage: String,
) {
    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier.padding(20.dp)
                .widthIn(max = 300.dp)
                .size(300.dp)
                .heightIn(max = 200.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth(), // Đảm bảo rằng cột sẽ chiếm toàn bộ chiều rộng của Card
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    dialogTitle,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center, // Căn giữa tiêu đề
                    modifier = Modifier.fillMaxWidth() // Đảm bảo tiêu đề chiếm toàn bộ chiều rộng của cột
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    dialogMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center, // Căn giữa nội dung
                    modifier = Modifier.fillMaxWidth() // Đảm bảo nội dung chiếm toàn bộ chiều rộng của cột
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onConfirmation,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Căn nút vào giữa
                ) {
                    Text("Okay")
                }
            }
        }
    }
}
@Composable
fun RememberMeSwitch() {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Text(
            "Remember Me?", modifier = Modifier.padding(
                start =
                12.dp
            )
        )
    }
}

@Preview
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    if (showDialog) {
        DialogComponent(
            onConfirmation = { showDialog = false },
            dialogTitle = "Notification",
            dialogMessage = dialogMessage
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 24.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
// Logo
        Image(
            painter = painterResource(
                id =
                R.drawable.ic_launcher_foreground
            ),
            contentDescription = "Logo",
        )
        Spacer(modifier = Modifier.height(20.dp))
// Username TextField
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
        )
        Spacer(modifier = Modifier.height(8.dp))
// Password TextField
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        RememberMeSwitch()
        Spacer(modifier = Modifier.height(12.dp))
// Login Button
        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    // Thay vì hiển thị Toast, thiết lập showDialog thành true để hiển thị dialog
                    showDialog = true
                    dialogMessage = "Login successful"
                } else {
                    // Thay vì hiển thị Toast, thiết lập showDialog thành true để hiển thị dialog
                    showDialog = true
                    dialogMessage = "Please enter username and password"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

    }
}