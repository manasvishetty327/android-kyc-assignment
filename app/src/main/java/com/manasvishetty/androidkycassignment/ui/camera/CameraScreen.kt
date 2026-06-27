package com.manasvishetty.androidkycassignment.ui.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.io.File
import androidx.navigation.NavController
import com.manasvishetty.androidkycassignment.ui.accounts.AccountsViewModel
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun CameraScreen(
    customerId: Int,
    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val imageCapture = remember { ImageCapture.Builder().build() }

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasPermission = granted
        }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    if (hasPermission) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                CameraPreview(
                    imageCapture = imageCapture
                )
            }

            Button(
                onClick = {
                    capturePhoto(
                        context = context,
                        imageCapture = imageCapture,
                        onPhotoCaptured = { path ->
                            viewModel.verifyCustomer(
                                customerId = customerId,
                                selfiePath = path
                            )
                            navController.popBackStack()
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Capture Selfie")
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Text("Camera permission required")
        }
    }
}

@Composable
fun CameraPreview(
    imageCapture: ImageCapture
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val cameraProviderFuture =
                ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build()
                preview.surfaceProvider = previewView.surfaceProvider

                val cameraSelector =
                    CameraSelector.DEFAULT_FRONT_CAMERA

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )

            }, ContextCompat.getMainExecutor(context))

            previewView
        }
    )
}

fun capturePhoto(
    context: Context,
    imageCapture: ImageCapture,
    onPhotoCaptured: (String) -> Unit
) {
    val file = File(
        context.externalMediaDirs.first(),
        "${System.currentTimeMillis()}.jpg"
    )

    val outputOptions =
        ImageCapture.OutputFileOptions.Builder(file).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(
                outputFileResults: ImageCapture.OutputFileResults
            ) {
                onPhotoCaptured(file.absolutePath)
            }

            override fun onError(
                exception: ImageCaptureException
            ) {
                exception.printStackTrace()
            }
        }
    )
}