import React, { useState, useEffect } from 'react';
import { Layout, Select, Card, Button, Badge, message, Row, Col, Typography, Image, Spin } from 'antd';
import { ShoppingCartOutlined, PlusOutlined, LogoutOutlined, ArrowLeftOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';
import './MainApp.css';

const { Header, Content } = Layout;
const { Title, Text } = Typography;
const { Option } = Select;

const MainApp = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [selectedRestaurant, setSelectedRestaurant] = useState(null);
  const [selectedRestaurantMenu, setSelectedRestaurantMenu] = useState([]);
  const [cart, setCart] = useState([]);
  const [loading, setLoading] = useState(true);
  const [menuLoading, setMenuLoading] = useState(false);
  const navigate = useNavigate();

  // Fetch restaurants on component mount
  useEffect(() => {
    fetchRestaurants();
  }, []);

  // Fetch restaurants from backend
  const fetchRestaurants = async () => {
    try {
      const response = await fetch('/api/restaurants');
      if (response.ok) {
        const data = await response.json();
        setRestaurants(data);
      } else {
        message.error('Failed to load restaurants');
      }
    } catch (error) {
      console.error('Error fetching restaurants:', error);
      message.error('Network error loading restaurants');
    } finally {
      setLoading(false);
    }
  };

  // Fetch menu items for selected restaurant
  const fetchMenuItems = async (restaurantId) => {
    setMenuLoading(true);
    try {
      const response = await fetch(`/api/menu-items/restaurant/${restaurantId}`);
      if (response.ok) {
        const data = await response.json();
        setSelectedRestaurantMenu(data);
      } else {
        message.error('Failed to load menu items');
      }
    } catch (error) {
      console.error('Error fetching menu items:', error);
      message.error('Network error loading menu items');
    } finally {
      setMenuLoading(false);
    }
  };

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
          <div className="header-center">
            {selectedRestaurant && (
              <Select
                placeholder="Switch restaurant"
                style={{ width: 200 }}
                size="middle"
                value={selectedRestaurant?.id}
                onChange={(value) => {
                  const restaurant = restaurants.find(r => r.id === value);
                  setSelectedRestaurant(restaurant);
                  if (restaurant) {
                    fetchMenuItems(restaurant.id);
                  }
                }}
              >
                {restaurants.map(restaurant => (
                  <Option key={restaurant.id} value={restaurant.id}>
                    {restaurant.name}
                  </Option>
                ))}
              </Select>
            )}
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
        {loading ? (
          <div style={{ textAlign: 'center', padding: '50px' }}>
            <Spin size="large" />
            <div style={{ marginTop: 16 }}>Loading restaurants...</div>
          </div>
        ) : selectedRestaurant ? (
          <div className="restaurant-content">
            <div className="restaurant-header">
              <Button 
                type="default" 
                onClick={() => setSelectedRestaurant(null)}
                style={{ marginBottom: 16 }}
                icon={<ArrowLeftOutlined />}
              >
                ← Back to Restaurants
              </Button>
              <Image
                src={selectedRestaurant.imageUrl}
                alt={selectedRestaurant.name}
                className="restaurant-image"
              />
              <Title level={2}>{selectedRestaurant.name}</Title>
            </div>
            
            {menuLoading ? (
              <div style={{ textAlign: 'center', padding: '50px' }}>
                <Spin size="large" />
                <div style={{ marginTop: 16 }}>Loading menu...</div>
              </div>
            ) : (
              <Row gutter={[16, 16]} className="menu-grid">
                {selectedRestaurantMenu.map(item => (
                  <Col xs={24} sm={12} md={8} lg={6} key={item.id}>
                    <Card
                      hoverable
                      className="menu-item-card"
                      cover={
                        <Image
                          alt={item.name}
                          src={item.imageUrl}
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
            )}
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
                onChange={(value) => {
                  const restaurant = restaurants.find(r => r.id === value);
                  setSelectedRestaurant(restaurant);
                  if (restaurant) {
                    fetchMenuItems(restaurant.id);
                  }
                }}
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