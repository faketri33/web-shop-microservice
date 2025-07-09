// src/components/ProductSlider.js
import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import ProductCard from './ProductCard';

const ProductSlider = ({ products, title }) => {
  return (
    <div className="my-8">
      {title && <h3 className="text-xl font-semibold mb-4">{title}</h3>}
      <Swiper
        spaceBetween={16}
        breakpoints={{
          320: { slidesPerView: 1.1 },
          640: { slidesPerView: 2.2 },
          768: { slidesPerView: 3 },
          1024: { slidesPerView: 4 },
        }}
      >
        {products.map((product) => (
          <SwiperSlide key={product.id}>
            <ProductCard product={product} />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};

export default ProductSlider;
