module.exports = {
  mode: 'jit', // JIT 모드 설정
  content: [
    './src/**/*.{js,jsx,ts,tsx,html}', 
    './main/**/*.html'
  ],
  theme: {
    extend: {
      screens: {
        'max-1400': { 'max': '1400px'},
        'min-1280': { 'min': '1281px'},
        'max-1280': { 'max': '1280px'},
        'max-1160': { 'max': '1160px'},
        'max-920': { 'max': '920px'},
        'max-lg': { 'max': '1024px' },
        'min-lg': { 'min': '1025px' },
        'max-md': { 'max': '768px'},
        'max-sd': { 'max': '640px'},
      },
    },
  },
  variants: {
    extend: {

    },
  },
  plugins: [],
}
