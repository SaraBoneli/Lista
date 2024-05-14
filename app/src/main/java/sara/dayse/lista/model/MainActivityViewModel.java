package sara.dayse.lista.model;

import android.view.View;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    List<MyItem> items = new ArrayList<>();//guarda a lista de itens cadastrados

    public List<MyItem> getItems() {//obtém a lista de itens
        return items;//obtém a lista de itens
    }
}
