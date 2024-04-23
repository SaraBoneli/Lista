package sara.dayse.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import sara.dayse.lista.R;
import sara.dayse.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST =1;
    List<MyItem> itens = new ArrayList<>();//lista que está definida como atributo de MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);//obtém botão
        fabAddItem.setOnClickListener(new View.OnClickListener() { //registra um ouvidor de cliques
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewItemActivity.class);//Intent explícito para navegar para NewItemAcitvity
                startActivityForResult(i, NEW_ITEM_REQUEST);//assume que a Activity destino (NewItemAcitivity)
                //irá retornar com dados em algum momento para
                //a Activity que iniciou a navegação (MainActivity)

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) { //se a condição de retorno foi cumprida
            if (resultCode == Activity.RESULT_OK) {//se a condição de retorno foi cumprida
                MyItem myItem = new MyItem();//cria uma instância de MyItem para guardar os dados do item
                myItem.title = data.getStringExtra("title");//obtém os dados retornados por NewItemActivity
                // e os guarda dentro de myItem
                myItem.description = data.getStringExtra("description");//obtém os dados retornados por NewItemActivity e os
                //guarda dentro de myItem
                myItem.photo = data.getData();//obtém os dados retornados por NewItemActivity e os
                //guarda dentro de myItem
                itens.add(myItem);//adiciona o item a uma lista de itens
            }
        }
    }
}