import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  // GitHub Pages serves project sites under /<repository-name>/.
  // Local development and custom-domain hosts continue to use the root path.
  base: process.env.GITHUB_PAGES === 'true' ? '/boutique-flow-template/' : '/',
  server: {
    port: 3000
  }
});
