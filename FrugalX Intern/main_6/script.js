// import * as tf from '@tensorflow/tfjs';

async function loadModel() {
  // const model = await tf.loadLayersModel('https://drive.google.com/file/d/1GfseFNz8JDISrzUBCRIqbPoBdSEAh6EF/view?usp=sharing');
  const model = await tf.loadLayersModel('./model.json');
  console.log('Model loaded:', model);
}

loadModel();

function preprocessImage(image) {
  const resizedImage = tf.image.resizeBilinear(image, [160, 160]);
  //const preprocessedImage = resizedImage.div(127.5).sub(1.0);
  const preprocessedImage = resizedImage.toFloat();
  return preprocessedImage;
}

async function classifyImage() {
  const image = document.getElementById('previewImage');
  const resultElement = document.getElementById('result');

  const preprocessedImage = preprocessImage(tf.browser.fromPixels(image));

  const model = await tf.loadLayersModel('./model.json');

  const prediction = model.predict(preprocessedImage.expandDims(0));
  const score = prediction.dataSync()[0];
  console.log(score)
  prediction.dispose();

  if (score >= 0.5) {
    resultElement.textContent = 'Non-drowsy';
  } else {
    resultElement.textContent = 'Drowsy';
  }

  preprocessedImage.dispose();
}


const imageInput = document.getElementById('imageInput');
// Add event listener to file input element
imageInput.addEventListener('change', (event) => {

  if (imageInput.files.length > 0) {
    const file = imageInput.files[0];
    const reader = new FileReader();

    reader.onload = async (event) => {
      const image = new Image();
      image.onload = async () => {
        const resultElement = document.getElementById('result');
        resultElement.textContent = '';
        const previewImageElement = document.getElementById('previewImage');
        previewImageElement.src = event.target.result;
      };
      image.src = event.target.result;
    };

    reader.readAsDataURL(file);
  } else {
    alert('Please select an image.');
  }
})
