# Notes on Project Setup

## Create React App

- Using this since all other tools... are just other tools
- create-react-app/react-scripts are pre-integrated with specific tools like: webpack, babel, postcss, etc... you can view these at any time by running "npm run eject"; however this is one way... so best for investigation only and not to check in if can be avoided.

## Tailwindcss

- npm install tailwindcss
- npx tailwindcss init -p
  - Will create a tailwind.config.js
    - content element needs to be updated to identify the files that contain tailwindcss utility functions
  - Will create postcss.config.js

add tailwindcss directives to your main css file:

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```