const categories = [
  { id: 1, name: 'Одежда', image: '/images/clothing.jpg' },
  { id: 2, name: 'Электроника', image: '/images/electronics.jpg' },
  { id: 3, name: 'Дом и кухня', image: '/images/home.jpg' },
];

const CategoryList = () => {
  return (
    <div className="my-6">
      <h3 className="text-xl font-semibold mb-4">Категории</h3>
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        {categories.map((cat) => (
          <div key={cat.id} className="border rounded-lg overflow-hidden shadow hover:shadow-md transition">
            <img src={cat.image} alt={cat.name} className="w-full h-40 object-cover" />
            <div className="p-2 text-center font-medium">{cat.name}</div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CategoryList;