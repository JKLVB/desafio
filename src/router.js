import Vue from "vue";
import Router from "vue-router";
import Produto from './components/Produtos';

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/produtos",
      name: "produtos",
      component: Produto
    }
  ]
});