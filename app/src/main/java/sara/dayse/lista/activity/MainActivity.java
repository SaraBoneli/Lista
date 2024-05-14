package sara.dayse.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import sara.dayse.lista.R;
import sara.dayse.lista.adapter.MyAdapter;
import sara.dayse.lista.model.MainActivityViewModel;
import sara.dayse.lista.model.MyItem;
import sara.dayse.lista.util.Util;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST =1;

    MyAdapter myAdapter;
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

        RecyclerView rvItens = findViewById(R.id.rvItens);//obtém RecycleView

        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);//// obtemos o ViewModel
        List<MyItem>items = vm.getItems();// obtemos a lista de itens que o ViewModel guarda
        myAdapter = new MyAdapter(this,items);//cria o MyAdapter
        rvItens.setAdapter(myAdapter);//seta MyAdapter no RecycleView

        rvItens.setHasFixedSize(true);//indica que não há variação de tamanho entre os itens da lista.

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);// cria um
        //gerenciador de layout do tipo linear
        rvItens.setLayoutManager(layoutManager);//seta o gerenciador no RecycleView.

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL);//cria um decorador para a lista
        rvItens.addItemDecoration(dividerItemDecoration);//seta o decorador no RecycleView
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
                Uri selectedPhotoUri  = data.getData();//obtém os dados retornados por NewItemActivity e os
                //guarda dentro de myItem

                try {
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoUri, 100, 100);//carrega imagem e guarda no Bitmap
                    myItem.photo = photo;//guarda Bitmap no objeto tipo MyItem
                } catch (FileNotFoundException e) {
                    e.printStackTrace();//se o arquivo de imagem não for encontrado,a exceção é disparada

                }

                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);// obtemos o ViewModel
                List<MyItem> items = vm.getItems();// obtemos a lista de itens que o ViewModel guarda
                items.add(myItem);//adiciona o item a uma lista de itens
                myAdapter.notifyItemInserted(items.size()-1);
            }
        }
    }
}