// src/pages/HomePage.js
import React from 'react';
import ProductSlider from '../components/ProductSlider';
import PromoBanner from '../components/PromoBanner';

const sampleProducts = [
  { id: 1, name: 'Наушники', price: '3990₽', image: '/images/headphones.jpg' },
  { id: 2, name: 'Футболка', price: '1290₽', image: '/images/tshirt.jpg' },
  { id: 3, name: 'Сковорода', price: '990₽', image: '/images/pan.jpg' },
  { id: 4, name: 'Рюкзак', price: '2290₽', image: '/images/backpack.jpg' },
  { id: 5, name: 'Кофеварка', price: '4990₽', image: '/images/coffee.jpg' },
];

const HomePage = () => {
  return (
    <div className="container mx-auto px-4 py-6">
        <PromoBanner/>
      <ProductSlider products={sampleProducts} title="🔥 Популярные товары" />
    </div>
  );
};

export default HomePage;
