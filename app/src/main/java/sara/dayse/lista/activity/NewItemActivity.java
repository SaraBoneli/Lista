package sara.dayse.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import sara.dayse.lista.R;
import sara.dayse.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);//obtém ViewModel

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();//obtém o endereço URI guardado dentro do ViewModel
        if (selectPhotoLocation != null) { // se o endereço não é nulo,significa que o usuário havia escolhido uma imagem antes de
            //rotacionar a tela
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);//seta a imagem no ImageView da tela
            imvfotoPreview.setImageURI(selectPhotoLocation);//seta a imagem no ImageView da tela
        }

        ImageButton imgCI = findViewById(R.id.imbCI);//obtém o ImageButton
        imgCI.setOnClickListener(new View.OnClickListener() {//define o ouvidor de cliques no botão
            @Override
            public void onClick(View v) { //executa a abertura da galeria para escolha da foto
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//criação de um Intent
                //implícito com ACTION do tipo abrir documento
                photoPickerIntent.setType("image/*");//seta no Intent criado que estamos interessados apenas em documentos
                //com mimetype “image/*” (qualquer tipo de imagem)
                startActivityForResult(photoPickerIntent,PHOTO_PICKER_REQUEST);//executa o
                //Intent através do uso do método startActivityForResult

            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem);//obtém o Button

        btnAddItem.setOnClickListener(new View.OnClickListener() {//seta o ouvidor de cliques do Button
            @Override
            public void onClick(View v) {//verifica se os campos foram preenchidos pelo usuário
                Uri photoselected = vm.getSelectPhotoLocation();//pega as imagens de vm e salva

                if (photoselected == null) { //se nenhuma foto foi escolhida
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!",
                            Toast.LENGTH_LONG) .show();
                    return;//exibe mensagem de erro
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText(). toString();
                if (title.isEmpty()) {//se o  campo de título estiver vazio
                    Toast.makeText(NewItemActivity.this, "È necessário inserir um título", Toast.LENGTH_LONG) .show();
                    return;//exibe mensagem de erro
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText(). toString();
                if (description.isEmpty()) {//se o  campo de descrição estiver vazio
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG) .show();
                    return;//exibe mensagem de erro
                }

                Intent i = new Intent();//cria Intent
                i.setData(photoselected);//seta o Uri da imagem escolhida dentro do Intent
                i.putExtra("title", title);//seta título
                i.putExtra("description", description);//seta descrição
                setResult(Activity.RESULT_OK, i);//indica o resultado da Activity
                finish();// Activity finalizada
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {//chamando método para entrega
        // de 3 parâmetros
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {//verifica se requestCode é referente ao fornecido na chamada de
            //startActiviyForResult com id PHOTO_PICKER_REQUEST
            if(resultCode == Activity.RESULT_OK) {//verifica se resultCode é um código de sucesso
                Uri photoSelected = data.getData();//obtém o resultado,caso as duas condições acima sejam verdadeiras
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);//obtém o ImageView
                imvfotoPreview.setImageURI(photoSelected);//exibe a imagem que está associada ao endereço setando o URI.

                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);//obtém o ViewModel
                vm.setSelectPhotoLocation(photoSelected);//guarda dentro do ViewModel o endereço URI da imagem escolhida pelo usuário
            }
        }
    }
}

