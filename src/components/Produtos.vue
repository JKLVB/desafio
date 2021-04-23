<template>
  <div class="produto">
      <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Produtos</a>
      </div>
    </nav>

    <div class="container">

      <form>

          <label>Nome</label>
          <input type="text" placeholder="Nome" v-model="produtoNew.nome" >
          <label>Categoria</label>
          <input type="text" placeholder="Categoria" v-model="produtoNew.categoria" >
          <label>Valor</label>
          <input type="number" step="0.01" placeholder="Valor" v-model="produtoNew.valor" >
          <label>Código</label>
          <input type="number" placeholder="Código" v-model="produtoNew.codigo" >
          <label>Status</label>
          <input type="text" placeholder="Status" v-model="produtoNew.status" >

          <td>
            <button class="waves-effect waves-light btn-small" @click="salvar(produtoNew)">Salvar<i class="material-icons left">save</i></button>
          </td>
          <td>
            <button class="waves-effect waves-light btn-small">Atualizar<i class="material-icons left">refresh</i></button>
          </td>

      </form>
          
      <table>

        <thead>

          <tr>
            <th>NOME</th>
            <th>CATEGORIA</th>
            <th>VALOR</th>
            <th>CÓDIGO</th>
            <th>STATUS</th>
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr v-for="produto in produtos" :key="produto.id">

            <td>{{produto.nome}}</td>
            <td>{{produto.categoria}}</td>
            <td>{{produto.valor}}</td>
            <td>{{produto.codigo}}</td>
            <td>{{produto.status}}</td>

            <td>
              <button class="waves-effect btn-small blue darken-1" @click="editar(produto)"><i class="material-icons">create</i></button>
              <button class="waves-effect btn-small red darken-1" @click="deletar(produto.id)"><i class="material-icons">delete_sweep</i></button>
            </td>
              
          </tr>

        </tbody>
      
      </table>

    </div>
  </div>
</template>

<script>

import produtoServicoData from '../services/produtoServicoData';

export default {
    name: 'App',

    data(){
        return {
            produtos: [],
            produtoNew: {
            id: '',
            nome: '',
            categoria: '',
            valor: null,
            codigo: null,
            status: ''
            }           
        }   
    },
    
    mounted(){
      produtoServicoData.getAll().then(resposta => {
        console.log(resposta.data);
        this.produtos = resposta.data;
      })
    },
    methods:{
      salvar(produto){
        if(!this.produtos.id){
          produtoServicoData.create(produto);
          alert('Salvo com sucesso!');
        } else{
          produtoServicoData.update(produto);
          alert('Atualizado com sucesso!');
        }
      },
      deletar(id){
        produtoServicoData.delete(id);
      },
      editar(produto){
        this.produtoNew = produto;
      }
    }
}
</script>

<style>
  
</style>