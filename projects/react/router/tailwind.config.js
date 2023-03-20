/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{tsx, ts}'
  ],
  theme: {
    extend: {
      animation: {
        spinlogo: 'spinlogo infinite 20s linear'
      },
      keyframes: {
        spinlogo: {
          'from': { transform: 'rotate(0deg)' },
          'to': { transform: 'rotate(360deg)' },
        }
      }
    },
  },
  plugins: [],
}
