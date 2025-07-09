// src/components/ProductCard.js
import React from 'react';

const ProductCard = ({ product }) => {
  return (
    <div className="border rounded-lg shadow hover:shadow-md transition p-4 bg-white flex flex-col items-center text-center">
      <img
        src={product.image}
        alt={product.name}
        className="w-full h-40 object-cover mb-3 rounded"
      />
      <h4 className="font-semibold text-lg">{product.name}</h4>
      <p className="text-blue-600 font-bold mb-2">{product.price}</p>
      <button className="mt-auto bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded text-sm">
        Добавить в корзину
      </button>
    </div>
  );
};

export default ProductCard;
