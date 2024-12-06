<template>
  <div id="app">
    <header>
      <h1>My Global Header</h1>
      <nav>
        <router-link to="/">Home</router-link>
        <router-link to="/products">Products</router-link>
      </nav>
    </header>

    <main>
      <router-view />
    </main>

    <footer>
      <p>My Global Footer</p>
    </footer>
  </div>
</template>

<script setup>
// Usually App.vue is minimal in logic. You can still import stores or global plugins here if needed.
</script>

<style>
/* Global styles for layout can go here */
</style>
