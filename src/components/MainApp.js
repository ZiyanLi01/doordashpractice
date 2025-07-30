import React, { useState } from 'react';
import { Layout, Select, Card, Button, Badge, message, Row, Col, Typography, Image } from 'antd';
import { ShoppingCartOutlined, PlusOutlined, LogoutOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';
import './MainApp.css';

const { Header, Content } = Layout;
const { Title, Text } = Typography;
const { Option } = Select;

// Mock data for restaurants and their menus
const restaurants = [
  {
    id: 1,
    name: 'Burger King',
    image: 'https://via.placeholder.com/300x200/FF6B35/FFFFFF?text=Burger+King',
    menu: [
      {
        id: 1,
        name: 'Whopper',
        description: 'Flame-grilled beef patty with fresh lettuce, tomatoes, mayo, pickles, and onions',
        price: 8.99,
        image: 'https://via.placeholder.com/200x150/FF6B35/FFFFFF?text=Whopper'
      },
      {
        id: 2,
        name: 'Chicken Royale',
        description: 'Crispy chicken breast with lettuce and creamy mayo',
        price: 7.99,
        image: 'https://via.placeholder.com/200x150/FF6B35/FFFFFF?text=Chicken+Royale'
      },
      {
        id: 3,
        name: 'French Fries',
        description: 'Golden crispy fries seasoned with salt',
        price: 3.99,
        image: 'https://via.placeholder.com/200x150/FF6B35/FFFFFF?text=French+Fries'
      }
    ]
  },
  {
    id: 2,
    name: 'Tofu House',
    image: 'https://via.placeholder.com/300x200/4CAF50/FFFFFF?text=Tofu+House',
    menu: [
      {
        id: 4,
        name: 'Spicy Tofu Soup',
        description: 'Traditional Korean spicy tofu soup with vegetables',
        price: 12.99,
        image: 'https://via.placeholder.com/200x150/4CAF50/FFFFFF?text=Spicy+Tofu+Soup'
      },
      {
        id: 5,
        name: 'Bibimbap',
        description: 'Mixed rice bowl with vegetables, egg, and gochujang sauce',
        price: 14.99,
        image: 'https://via.placeholder.com/200x150/4CAF50/FFFFFF?text=Bibimbap'
      },
      {
        id: 6,
        name: 'Kimchi Fried Rice',
        description: 'Fried rice with kimchi, vegetables, and egg',
        price: 11.99,
        image: 'https://via.placeholder.com/200x150/4CAF50/FFFFFF?text=Kimchi+Fried+Rice'
      }
    ]
  },
  {
    id: 3,
    name: 'Fashion Work',
    image: 'https://via.placeholder.com/300x200/9C27B0/FFFFFF?text=Fashion+Work',
    menu: [
      {
        id: 7,
        name: 'Gourmet Burger',
        description: 'Premium beef burger with artisanal cheese and special sauce',
        price: 16.99,
        image: 'https://via.placeholder.com/200x150/9C27B0/FFFFFF?text=Gourmet+Burger'
      },
      {
        id: 8,
        name: 'Truffle Fries',
        description: 'Crispy fries with truffle oil and parmesan cheese',
        price: 8.99,
        image: 'https://via.placeholder.com/200x150/9C27B0/FFFFFF?text=Truffle+Fries'
      },
      {
        id: 9,
        name: 'Caesar Salad',
        description: 'Fresh romaine lettuce with caesar dressing and croutons',
        price: 13.99,
        image: 'https://via.placeholder.com/200x150/9C27B0/FFFFFF?text=Caesar+Salad'
      }
    ]
  }
];

const MainApp = () => {
  const [selectedRestaurant, setSelectedRestaurant] = useState(null);
  const [cart, setCart] = useState([]);
  const navigate = useNavigate();

  const addToCart = (item) => {
    const existingItem = cart.find(cartItem => cartItem.id === item.id);
    if (existingItem) {
      setCart(cart.map(cartItem => 
        cartItem.id === item.id 
          ? { ...cartItem, quantity: cartItem.quantity + 1 }
          : cartItem
      ));
    } else {
      setCart([...cart, { ...item, quantity: 1 }]);
    }
    message.success(`${item.name} added to cart!`);
  };

  const removeFromCart = (itemId) => {
    setCart(cart.filter(item => item.id !== itemId));
  };

  const updateQuantity = (itemId, quantity) => {
    if (quantity === 0) {
      removeFromCart(itemId);
    } else {
      setCart(cart.map(item => 
        item.id === itemId ? { ...item, quantity } : item
      ));
    }
  };

  const checkout = () => {
    if (cart.length === 0) {
      message.warning('Your cart is empty!');
      return;
    }
    message.success('Order placed successfully! Your cart has been cleared.');
    setCart([]);
  };

  const getTotalPrice = () => {
    return cart.reduce((total, item) => total + (item.price * item.quantity), 0);
  };

  const handleLogout = () => {
    // Clear stored user data
    localStorage.removeItem('user');
    message.success('Logged out successfully!');
    navigate('/login');
  };

  return (
    <Layout className="main-app">
      <Header className="app-header">
        <div className="header-content">
          <div className="header-left">
            <Title level={3} className="app-title" style={{ color: 'white', margin: 0 }}>Mini DoorDash</Title>
          </div>
          <div className="header-right">
            <Badge count={cart.length} showZero>
              <Button 
                type="primary" 
                icon={<ShoppingCartOutlined />}
                onClick={() => message.info('Cart functionality is active!')}
              >
                Cart (${getTotalPrice().toFixed(2)})
              </Button>
            </Badge>
            <Button 
              icon={<LogoutOutlined />}
              onClick={handleLogout}
              style={{ marginLeft: 10 }}
            >
              Logout
            </Button>
          </div>
        </div>
      </Header>

      <Content className="app-content">
        {selectedRestaurant ? (
          <div className="restaurant-content">
            <div className="restaurant-header">
              <Image
                src={selectedRestaurant.image}
                alt={selectedRestaurant.name}
                className="restaurant-image"
              />
              <Title level={2}>{selectedRestaurant.name}</Title>
            </div>
            
            <Row gutter={[16, 16]} className="menu-grid">
              {selectedRestaurant.menu.map(item => (
                <Col xs={24} sm={12} md={8} lg={6} key={item.id}>
                  <Card
                    hoverable
                    className="menu-item-card"
                    cover={
                      <Image
                        alt={item.name}
                        src={item.image}
                        className="menu-item-image"
                      />
                    }
                    actions={[
                      <Button 
                        type="primary" 
                        icon={<PlusOutlined />}
                        onClick={() => addToCart(item)}
                        className="add-to-cart-btn"
                      >
                        Add to Cart
                      </Button>
                    ]}
                  >
                    <Card.Meta
                      title={item.name}
                      description={
                        <div>
                          <Text type="secondary">{item.description}</Text>
                          <div className="price-section">
                            <Text strong className="price">${item.price}</Text>
                          </div>
                        </div>
                      }
                    />
                  </Card>
                </Col>
              ))}
            </Row>
          </div>
        ) : (
          <div className="welcome-section">
            <Title level={2}>Welcome to Mini DoorDash!</Title>
            <Text>Please select a restaurant from the dropdown to view the menu.</Text>
            <div style={{ marginTop: 24 }}>
              <Select
                placeholder="Select a restaurant"
                style={{ width: 300 }}
                size="large"
                onChange={(value) => setSelectedRestaurant(restaurants.find(r => r.id === value))}
                value={selectedRestaurant?.id}
              >
                {restaurants.map(restaurant => (
                  <Option key={restaurant.id} value={restaurant.id}>
                    {restaurant.name}
                  </Option>
                ))}
              </Select>
            </div>
          </div>
        )}

        {/* Cart Sidebar */}
        {cart.length > 0 && (
          <div className="cart-sidebar">
            <Title level={4}>Your Cart</Title>
            {cart.map(item => (
              <div key={item.id} className="cart-item">
                <div className="cart-item-info">
                  <Text strong>{item.name}</Text>
                  <Text type="secondary">${item.price}</Text>
                </div>
                <div className="cart-item-controls">
                  <Button 
                    size="small" 
                    onClick={() => updateQuantity(item.id, item.quantity - 1)}
                  >
                    -
                  </Button>
                  <span className="quantity">{item.quantity}</span>
                  <Button 
                    size="small" 
                    onClick={() => updateQuantity(item.id, item.quantity + 1)}
                  >
                    +
                  </Button>
                </div>
              </div>
            ))}
            <div className="cart-total">
              <Text strong>Total: ${getTotalPrice().toFixed(2)}</Text>
            </div>
            <Button 
              type="primary" 
              block 
              onClick={checkout}
              className="checkout-btn"
            >
              Checkout
            </Button>
          </div>
        )}
      </Content>
    </Layout>
  );
};

export default MainApp; 