module.exports = {
  mode: 'jit', // JIT 모드 설정
  content: [
    './src/**/*.{js,jsx,ts,tsx,html}', 
    './main/**/*.html'
  ],
  theme: {
    extend: {},
  },
  variants: {
    extend: {
      screens: {
        'min-1280': { 'min': '1281px'},
        'max-1160': { 'max': '1160px'},
        'max-lg': { 'max': '1024px' },
        'min-lg': { 'min': '1025px' },
        'max-md': { 'max': '768px'},
        'max-sd': { 'max': '640px'},
      },
    },
  },
  plugins: [],
}
