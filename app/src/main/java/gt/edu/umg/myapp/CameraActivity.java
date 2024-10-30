package gt.edu.umg.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {
//Declaracion de bariables para el boton y la imagen
    Button btnCamarA; //boton para abrir camara
    ImageView imgView; //boton para visualizar la imagen capturada


    //metodo que se llama para la actividad requerida
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
// sea asocian los elementos del diseño con las variables de la clase
        btnCamarA = findViewById(R.id.btnCamarA);
        imgView = findViewById(R.id.imageView);

        btnCamarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
    }
//el metodo que abre la camara
    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //verifica si se tomo la foto
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap); //muestra la imagen capturada en el image view
        }
    }
}
