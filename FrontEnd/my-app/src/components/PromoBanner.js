// src/components/PromoBanner.tsx
import React from 'react';

const PromoBanner = () => {
  return (
    <div className="w-full bg-blue-500 text-white text-center py-6 px-4 rounded-lg mb-6">
      <h2 className="text-2xl font-bold">🔥 Летняя распродажа — до 50% на избранные товары!</h2>
      <p className="mt-2 text-sm">Только до конца недели</p>
    </div>
  );
};

export default PromoBanner;
