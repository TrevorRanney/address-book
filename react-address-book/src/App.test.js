import { render, screen, act } from '@testing-library/react';
import App from './App';

test('renders Contact Table Page', () => {
  render(<App />);
  const linkElement = screen.getByText(/Show Card View/i);
  expect(linkElement).toBeInTheDocument();
});

test('can switch to card view', () => {
  render(<App />);
  const showCardViewButton = screen.getByText(/Show Card View/i);
  act( () => {showCardViewButton.click()} );
  const showTableViewButton = screen.getByText(/Show Table View/i);
  expect(showTableViewButton).toBeInTheDocument();
});
