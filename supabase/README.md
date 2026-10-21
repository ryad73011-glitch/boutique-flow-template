# Optional Supabase backend foundation

This migration prepares a secure multi-store database foundation for a future merchant dashboard. It is **not connected to the current React storefront and has not been deployed to any Supabase project**.

## Included tables

- `stores`: store identity, slug, publication state, currency and public WhatsApp contact.
- `store_memberships`: per-store roles (`owner`, `admin`, `editor`). Creating a store atomically creates its owner membership.
- `products`: localized names/descriptions, DZD price, size options, optional inventory count and draft/active/archived state.
- `orders` and `order_items`: private order records with item-name/price snapshots and status tracking.

## Security model

- Row Level Security (RLS) is enabled on every table.
- Anonymous visitors can read only published stores and active products belonging to published stores.
- Authenticated store members can manage catalog data and private orders according to role policies. Only owners/admins can delete stores, products, or orders.
- Customers' names, phone numbers, delivery addresses, orders, and order items are never granted anonymous read access.
- Browser clients cannot grant themselves memberships or change roles. Inviting staff needs a trusted server-side flow.
- No anonymous order-insert policy exists. The current storefront sends a prepared WhatsApp message; it does not write orders to this schema.

## Apply and verify

1. Create or select a dedicated Supabase project for the store platform. Do not apply this to an unrelated production project.
2. Review `migrations/20261021030000_storefront_core.sql` and run it through the Supabase CLI migration workflow or SQL editor in a non-production project first.
3. Test the policies using separate anonymous, store-owner, editor, and unrelated authenticated accounts. Confirm that one store cannot read or mutate another store's draft products or customer orders.
4. Run a database linter/security review and test create/update/delete operations before using real customer data.
5. Only after verification, connect the frontend with the public project URL and anon/publishable key. Never place a service-role key in `VITE_*` variables or client-side code.

## Not implemented yet

The migration is only a database foundation. The frontend still uses its local demo product catalog, and there is no Supabase client, merchant sign-in, dashboard UI, image storage integration, public order submission endpoint, stock transaction logic, or staff invitation workflow. Do not advertise these capabilities until they are implemented and tested end to end.
