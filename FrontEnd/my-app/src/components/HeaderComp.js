import React, { useState } from 'react';
import { FiMenu, FiX, FiShoppingCart, FiUser } from 'react-icons/fi';

const Header = () => {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <header className="bg-white shadow-md fixed top-0 left-0 w-full z-50">
      <div className="max-w-7xl mx-auto px-4 py-3 flex justify-between items-center">
        {/* Логотип */}
        <div className="text-xl font-bold">WebShop</div>

        {/* Навигация (десктоп) */}
        <nav className="hidden md:flex space-x-6">
          <a href="#" className="text-gray-700 hover:text-black">Каталог</a>
          <a href="#" className="text-gray-700 hover:text-black">О нас</a>
          <a href="#" className="text-gray-700 hover:text-black">Контакты</a>
        </nav>

        {/* Иконки */}
        <div className="flex items-center space-x-4">
          <FiShoppingCart className="text-2xl cursor-pointer" />
          <FiUser className="text-2xl cursor-pointer" />

          {/* Бургер меню (мобильный) */}
          <div className="md:hidden">
            {menuOpen ? (
              <FiX className="text-2xl" onClick={() => setMenuOpen(false)} />
            ) : (
              <FiMenu className="text-2xl" onClick={() => setMenuOpen(true)} />
            )}
          </div>
        </div>
      </div>

      {/* Мобильное меню */}
      {menuOpen && (
        <div className="md:hidden bg-white px-4 pb-4">
          <a href="#" className="block py-2 border-b">Каталог</a>
          <a href="#" className="block py-2 border-b">О нас</a>
          <a href="#" className="block py-2 border-b">Контакты</a>
        </div>
      )}
    </header>
  );
};

export default Header;
