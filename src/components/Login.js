import React, { useState } from 'react';
import { Form, Input, Button, message } from 'antd';
import { UserOutlined, LockOutlined, EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';
import './Login.css';

const Login = () => {
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const onFinish = (values) => {
    setLoading(true);
    // Simulate login process
    setTimeout(() => {
      setLoading(false);
      message.success('Login successful!');
      navigate('/app');
    }, 1000);
  };

  return (
    <div className="login-container">
      {/* Header */}
      <div className="login-header">
        <div className="header-content">
          <h1 className="app-title">Mini DoorDash</h1>
          <Button 
            type="primary" 
            className="register-button"
            onClick={() => navigate('/signup')}
          >
            Register
          </Button>
        </div>
      </div>

      {/* Main Content */}
      <div className="login-main">
        <div className="login-form-container">
          <Form
            name="login"
            onFinish={onFinish}
            autoComplete="off"
            size="large"
          >
            <Form.Item
              name="username"
              rules={[{ required: true, message: 'Please input your username!' }]}
            >
              <Input
                prefix={<UserOutlined />}
                placeholder="Username"
                className="login-input"
              />
            </Form.Item>

            <Form.Item
              name="password"
              rules={[{ required: true, message: 'Please input your password!' }]}
            >
              <Input.Password
                prefix={<LockOutlined />}
                placeholder="Password"
                className="login-input"
                iconRender={(visible) => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
              />
            </Form.Item>

            <Form.Item>
              <Button 
                type="primary" 
                htmlType="submit" 
                className="login-button"
                loading={loading}
                block
              >
                Login
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default Login; 