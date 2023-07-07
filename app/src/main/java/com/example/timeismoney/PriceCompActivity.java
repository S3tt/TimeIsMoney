package com.example.timeismoney;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PriceCompActivity extends AppCompatActivity {

    private ArrayList<Produto> listaProdutos;
    private RecyclerView recyclerView;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_comp);

        listaProdutos = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProdutoAdapter(listaProdutos);
        recyclerView.setAdapter(adapter);

        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirDialogoAdicionar();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                listaProdutos.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void exibirDialogoAdicionar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_adicionar_produto, null);
        builder.setView(dialogView);

        final TextView txtNome = dialogView.findViewById(R.id.txtNome);
        final TextView txtValor = dialogView.findViewById(R.id.txtValor);

        builder.setTitle("Adicionar Produto")
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome = txtNome.getText().toString();
                        double valor = Double.parseDouble(txtValor.getText().toString());

                        Produto produto = new Produto(nome, valor);
                        listaProdutos.add(produto);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        builder.create().show();
    }

    private void exibirDialogoEditar(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_editar_produto, null);
        builder.setView(dialogView);

        final TextView txtNome = dialogView.findViewById(R.id.txtNome);


        final TextView txtValor = dialogView.findViewById(R.id.txtValor);

        final Produto produto = listaProdutos.get(position);
        txtNome.setText(produto.getNome());
        txtValor.setText(String.valueOf(produto.getValor()));

        builder.setTitle("Editar Produto")
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome = txtNome.getText().toString();
                        double valor = Double.parseDouble(txtValor.getText().toString());

                        produto.setNome(nome);
                        produto.setValor(valor);
                        adapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        builder.create().show();
    }

    private class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

        private ArrayList<Produto> listaProdutos;

        public ProdutoAdapter(ArrayList<Produto> listaProdutos) {
            this.listaProdutos = listaProdutos;
        }

        @NonNull
        @Override
        public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
            return new ProdutoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
            final Produto produto = listaProdutos.get(holder.getAdapterPosition());
            holder.txtNome.setText(produto.getNome());
            holder.txtValor.setText(String.valueOf(produto.getValor()));

            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exibirDialogoEditar(holder.getAdapterPosition());
                }
            });
        }


        @Override
        public int getItemCount() {
            return listaProdutos.size();
        }

        public class ProdutoViewHolder extends RecyclerView.ViewHolder {
            TextView txtNome, txtValor;
            Button btnEditar;

            public ProdutoViewHolder(@NonNull View itemView) {
                super(itemView);
                txtNome = itemView.findViewById(R.id.txtNome);
                txtValor = itemView.findViewById(R.id.txtValor);
                btnEditar = itemView.findViewById(R.id.btnEditar);
            }
        }
    }

    private class Produto {
        private String nome;
        private double valor;

        public Produto(String nome, double valor) {
            this.nome = nome;
            this.valor = valor;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }
    }
}
